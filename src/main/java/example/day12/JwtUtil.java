package example.day12;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.data.redis.autoconfigure.DataRedisProperties.Lettuce.Cluster.Refresh;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component // SPRING MVC 패턴 객체가 아닌 일반 객체(빈) 생성 
public class JwtUtil {
    // @Value("${propertis파일내속성명}) , 속성값
    // propertis파일내 api인증키 또는 개발자 보안데이터들 넣어 안전하게 사용 목적
    @Value("${jwt.secret}")  
    private String key; 
    // sha알고리즘 + 비밀키(임의로) 조합 -> hmacSha
    private SecretKey secretKey;
    @PostConstruct // 객체 생성시 의존성( @Value )가 완료 된 후에 아래 메소드가 1번 호출 되도록 하는 어노테이션 
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor( key.getBytes( StandardCharsets.UTF_8 ) );
    }
    // 왜 토큰을 2개 만드는가?
    // Access는 요청할 때마다 서버로 보내서 탈취 위험이 큼 그래서 수명을 짧게 (1시간)
    // 근데 1시간마다 재로그인하면 불편해서 수명이 긴 Refresh를 따로 보관해두고 Access가 만료되면 Refresh를 보여주고 새 Access를 받음
    // 그리고 이 Refresh 토큰을 서버가 어디에 저장할지가 문제인데 여기서 Redis가 나옴 
    // [3] JWT Refresh 토큰 생성 메소드
    public String createRefreshToken( Long mno ){
        return Jwts.builder() // 토큰 생성 시작 
                .claim("type", "REFRESH")
                .subject( mno + "" )
                .issuedAt( new Date() )
                .expiration( new Date( new Date().getTime()+ 1000L * 60 * 60 * 24 * 7 ) ) // 액세스 토큰 보다 만료기간 길게(7일)
                // 1000(초) × 60(분) × 60(시간) × 24(하루) × 7(일주일)
                .signWith( secretKey )
                .compact(); // 생성된 토큰 문자열 반환 
    }
    // [1] JWT ACCESS 토큰 생성 메소드 
    public String createAccessToken( Long mno ){
        String jwt = Jwts.builder() // 토큰 생성 시작
                    .claim("type", "ACCESS")
                    .subject( mno+"" ) // 토큰에 들어갈 내용(playload)들( 주로 식별번호, 권한 )
                    .issuedAt( new Date() ) // 토큰 생성 시간 ,   
                    .expiration( new Date( new Date().getTime() + 1000L * 60 * 60 ) ) // 1시간
                    // new Date() 현재시간 , new Date().getTime() 현재시간초 , * 60(1분) * 60 (1시간)
                    .signWith(secretKey) // 비밀키로 전자서명 init()에서 만든 secretKey
                    .compact(); // 토큰 생성 끝 , 토큰정보 문자열(String) 로 반환 
        System.out.println( jwt ); // 콘솔 출력 (확인용)
        return jwt; 
    }

    // [2] JWT 토큰 검증 메소드
    public Long getMnoFromToken( String token ){
        try{  // 만약에 token 파싱(가져오기)이 실패이면 예외 발생한다.
            Claims claims = Jwts.parser() // 파싱
                            .verifyWith( secretKey ) // 전자서명 이용한 검증
                            .build()
                            .parseSignedClaims( token ) // 파싱할 토큰 
                            .getPayload(); // JWT 안에 payload 값 반환 
            Long mno = Long.parseLong( claims.getSubject() ) ; // payload 안에 subject 꺼내기 (문자열타입-->Long타입 변환 )
            System.out.println( mno );
            return mno; // 토큰 검증이 성공이면 회원번호 반환 
        }catch(Exception e ){
            return null; // 만약에 토큰이 없거나 문제가 있으면 null 반환 
        }
    }
}