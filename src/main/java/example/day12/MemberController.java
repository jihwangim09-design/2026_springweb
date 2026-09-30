package example.day12;



import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/member")
@RequiredArgsConstructor
// 백엔드에서 CORS허용 : 컨트롤러위에 @CrossOrigin 또는 config 파일 만들기 // 도메인이 다른 경우 allowCredentials 이용한 쿠키/세션 유지
// allowCredentials = "true" 얘는 쿠키를 포함하겠다?
// 프론트엔드에서 CORS허용 : await axios.post( url , body , { withCredentials: true } );
@CrossOrigin (origins = "http://localhost:5173" , allowCredentials = "true") 
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    // [1] 회원가입
    @PostMapping ("/signup")
    public boolean signup(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }
    // [2] 로그인 + 세션(인증 성공시 성공한 회원정보 저장/왜? 로그인 성공한 회원이 글쓰기/제품등록 등등 FK용도)
    // [2] 로그인 + 쿠키변경( 회원 식별(번호) 쿠키에 담아 클라이언트에 전송 )
    private final RedisTokenService redisTokenService;
    // [2] 로그인 
    @PostMapping("/login")
    public MemberDto login( @RequestBody MemberDto memberDto , HttpServletResponse response ){
        MemberDto result = memberService.login(memberDto); // 1. 서비스 에게 인증/로그인 확인 (기존 유지)
        if( result == null ) return null; // 로그인 실패시 
        // 4. 토큰(token) **2개** 발급 요청
        String accessToken = jwtUtil.createAccessToken( result.getMno() );
        String refreshToken = jwtUtil.createRefreshToken( result.getMno() );
        // 5. refeshToken 만 **레디스** 에 저장
        redisTokenService.setRefreshToken( result.getMno() , refreshToken);
        // 2. 로그인 성공 시 쿠키 2개 생성/발급 , 쿠키만료기간 == 토큰만료기간 동일권장
        ResponseCookie cookie1 = ResponseCookie.from("accessToken" , accessToken)
                                .path("/").maxAge(Duration.ofMinutes(30) ) // 30분
                                .httpOnly(true).secure(false).sameSite("Lax").build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken" , refreshToken)
                        .path("/").maxAge(Duration.ofDays(7) ) // 7일 
                        .httpOnly(true).secure(false).sameSite("Lax").build();

        // 3. 응답 헤더에 쿠키 2개 등록 , response.setHeader( )
        response.setHeader( HttpHeaders.SET_COOKIE  , cookie1.toString() );
        response.setHeader( HttpHeaders.SET_COOKIE  , cookie2.toString() );
        return result;
    }

    // 먼저 login을 보내고 다음에 me를 해야 나옴
    // [3] 내정보조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo( 
        // @CookieValue( value="쿠키명") ){ // 요청한 브라우저의 쿠키 가져오기 
        @CookieValue (value="login_member" , required = false ) String token ){
        //1. 만약에 token이 없다면 비로그인
        if( token == null ) return  null;
        // ******* 쿠키에 저장된 token 이용하여 회원번호 찾기 ******* 
        Long loginMno = jwtUtil.getMnoFromToken(token);
        return memberService.getMyInfo( loginMno );
    }

    // Talend API Tester  : /me -> /login -> /me -> /logout -> /me
    // [4] 로그아웃 + 쿠키
    @PostMapping ("/logout")
    public boolean logout( HttpServletResponse response ){
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0) 하여 재발급
        ResponseCookie cookie = ResponseCookie.from( "login_member", "")
                            .path("/") // 모든곳에서 로그아웃 가능하도록 , 전체 
                            .maxAge(0) // 바로 삭제
                            .httpOnly(true).secure(false)
                            .build();
        // 2.응답객체내 헤더에 쿠키 포함
        response.setHeader( HttpHeaders.SET_COOKIE, cookie.toString() );
        return true;
    }


    @GetMapping ("")
    public String test( HttpServletRequest request ){
        // 1) HttpServletRequest: HTTP 요청이 들어오면 요청 정보가 담겨 있는 서블릿 객체 , 반대(응답)는 HttpServletResponse
        System.out.println( request.getRemoteAddr() ); // 요청한 클라이언트의 IP
        System.out.println( request.getHeader("User-Agent") ); // 요청한 클라이언트 브라우저 정보
        System.out.println( request.getSession() ); // 요청한 클라이언트의 세션객체 정보
        // 2) 새션객체란? 톰캣 서버내 브라우저 마다 독립적인 저장소
        // 주로 : 로그인 성공 정보 , 인증번호(ex 비번찾기) , 비회원제장바구니 일시적인 휘발성 메모리
        HttpSession session = request.getSession(); // 세션객채네 여러개 정보 저장 가능
        System.out.println( session.getId() ); // 세션 식별번호
        System.out.println( session.getCreationTime() ); // 세션 생성시간
        System.out.println( session.getLastAccessedTime() ); // 세션 마지막접근 시간
        System.out.println( session.getMaxInactiveInterval() ); // 세션 생명주기(기본값30분) ex) 은행 15~30분 자동로그아웃


        // 3) 세션(전역변수대신에) 정보     저장=로그인/호출=마이페이지/삭제=로그아웃
        session.setAttribute("data", "사과"); //map(key:value) 키값구주로
        // data 이름(key)으로 사과(data) 저장  , 주의할점: value 타입은 Object 이라서 타입변환 필요
        System.out.println( session.getAttribute("data")); // key 이용한 value 호출
        session.invalidate(); // 세션 초기화    invalidate : 무효화
        return session.getId();
    }

    
}
