package example.day08;

import example.totalpractice1.Controller.ProductsController;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service 
public class ApiService {
    // 서비스키 안전하게 application.properties 에서 관리 , 즉 프로젝트간 api키는 github에 push 하지말자
    // notion/excel 에서 공유
    // 2. WebClient 객체 빌더패턴 생성
    // @Value ("${application.propretis속성명}")
    @Value ("${api.pubilc-data.service-key}")
    private String serviceKey;
    private WebClient webClient = WebClient.builder().build();
    // [1] 인천광역시 부평구 맛집 현황 JSON을 Map으로 

    // 1.
    public Map<String,Object> test1(){
        // 1. API 주소( 공공데이터 신청한 api 요청 url )
        String url ="https://api.odcloud.kr/api/15103411/v1/uddi:efd2cc22-353c-47f0-83e5-abc6dca54f6f";
        url += "?page="+1;
        url += "&perpage="+10;   
        url += "&serviceKey="+serviceKey;
        // 3. webClient 객체 이용한 api 요청 하고 응답받기
        Map<String,Object> response = webClient.get() // .http메소드명 http GET메소드
                .uri(url) // uri는 http 주소상에 자원(쿼리스트링)까지 포함
                .retrieve() // 요청 결과 반환 결과 수신
                .bodyToMono( Map.class ) // 응답 결과 content.type 직렬화/변환
                .block(); // 동기화
                return response;
        // 응답이 JSON이라서 bodyToMono(Map.class)로 바로 변환 가능
    }
    // [2] 국립중앙의료원_전국 약국 정보 조회 서비스 XML을 Map으로
    public Map<String,Object> test2(){
        // 1. API 주소( 공공데이터 신청한 api 요청 url )
        String url ="https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        url += "?serviceKey="+serviceKey;
        url += "&pageNo="+1;
        url += "&numOfRows="+10;
        // 인증키, 페이지 번호, 한 번에 가져올 개수 조립

        // 3. 주의할점 : WebClient 에서 xml 타입을 String 타입으로 가져오기
        String response = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class) // XML 타입 --String-> --> Map 직렬화/변환
                .block();

        // 일단 원본 그대로 문자열로 받아둠

        // 4. String타입 -> xml 타입 변환 , 
        XmlMapper xmlMapper = new XmlMapper(); // xml매퍼 객체 생성
        // XmlMapper는 "XML 문자열을 자바 객체(여기선 Map)로 변환해주는 도구
        
        // Map<String,Object> map = xmlMapper.readValue( xml문자열 , 타입명.class); // +일반예외
        try{
            Map<String,Object> map = xmlMapper.readValue( response , Map.class);
            return map;
        }catch( Exception e){System.out.println(e);}
        return null;
        // test2() (전국 약국 정보 API) → 응답이 XML이라서, Map.class로 바로 못 바꿈

        
    }
    // 엑셀 파일 같은 CSV를 읽어서, 한 줄(행)마다 하나의 Map으로, 그 Map들을 모아서 List로 만드는 과정
    // 3. 프로젝트내 resources>>static> 파일명.csv 
    // 프로젝트 안에 있는 CSV 파일을 읽어서 자바 데이터로 바꾸기 test1,2는 외부api 호출인데 3은 프로젝트 안에 있는 CSV파일을 읽기
    public List<Map<String,Object>> test3(){
        // Map<String,Object> = key는 String, value는 Object인 Map 하나를 담는 리스트 List<Map<String,Object>>
        // [ { } , { } , { }  ] 대괄호 배열안에 { } MAP이 들어감
        // 1. .csv파일 경로 , resources 이하 폴더 파일 경로 지정
        String fileName = "static/중소벤처기업부_벤처기업명단_20260521.csv";
        // 2. ClassPathResource 객체 이용하여 해당 경로내 파일 가져오기 [파일객체]
        ClassPathResource resource = new ClassPathResource(fileName);
        // ClassPathResource : classpath(컴파일된 리소스 폴더) 안에서 파일을 찾아주는 스프링 도구
        // 3. (대용량)파일들을 바이트로 읽어와서 바이트배열 저장 .getInputStream().readAllBytes(); , +일반예외
        List< Map<String,Object>> list = new ArrayList<>(); // 최종적으로 리턴받을 빈 리스트
        try{
            byte[] bytes = resource.getInputStream().readAllBytes();
            // 아직까지 bytes 안에는 CSV 파일의 내용이 숫자(바이트)들의 배열로 들어았음
            // getInputStream() : 파일을 읽을 수 있는 스트림(통로) 열기
            // .readAllBytes() → 그 통로로 파일 전체 내용을 바이트 배열로 한번에 읽어오기
        // 4. 한글 인코딩 , EUC-KR , CP949 , UTF-8
            InputStreamReader reader = new InputStreamReader( new java.io.ByteArrayInputStream(bytes) , Charset.forName("CP949") );
            // 바이트를 CP949 규칙으로 해석해서 실제 읽을 수 있는 글자로 바꿔주는 변환기를 만듦
        // 5. OpenCSV 이용하여 바이트들을 대입한다.
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        // reader로부터 텍스트를 받아서 콤마(,) 기준으로 한 줄씩 잘라줌 OpenCSV 라이브러리
        // 6. 주로 첫행은 제목(행) 가져오기 (key/속성명 사용할 예정)
        String[] headers = csvReader.readNext(); // CSV의 첫 줄은 제목행이니까 따로 먼저 읽어둠
        // 7. 나머지 행들은 반복문 이용하여 가져오기
        String[] values;
        while ( true ) { // 무한루프
            // 8.
                values = csvReader.readNext(); // 한줄읽어오기
                if( values == null) break; // 만약에 읽어온 데이터가 없으면 반복문 종료
            // 9. 반복문 이용하여 map 만들기
                Map<String,Object> row = new LinkedHashMap<>(); // 일반 HashMap은 넣은 순서를 보장 안 해줘서 결과가 뒤섞여 나올 수 있는데 LinkedHashMap은 넣은 순서 그대로 유지 CSV 컬럼 순서(회사명 → 대표자 → 업종)
                for( int index = 0 ; index < headers.length; index++){
                    row.put( headers[index] , values[index]);
                }
            // 10. list에 생성한 map추가
                list.add(row);
            
        }
        }catch( Exception e){System.out.println(e);}
        return list;
        
    }
}

// WebClient : 외부 API(여기선 공공데이터포털)에 HTTP 요청을 보내고 응답을 받아옴
// .retrieve : 요청 보내고 응답을 받아와라
// .bodyToMono(Map.class) : 받아온 응답 body(JSON)를 Map 타입으로 변환(직렬화)하겠다는
/*

    JSON VS XML VS CSV
        - JSON(자바스크립트객체) : { 속성명 : 속성값 , 속성명 : 속성명 }
        - XML(마크업) : <속성명>속성값</속성명>        implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.22.2'
        - CSV(,쉼표구분) : 값,값,값,값,값              implementation 'com.opencsv:opencsv:5.12.0'



    컬렉션프레임워크: List , Set , Map
    - List: 여러개 자료들을 인덱스로 구분하여 하나의 자료에 저장
        -> [ 값1,값2,값3 ]
    - Set: 여러개 자료들을 인덱스없이 하나의 자료의 저장
        -> ( 값1 , 값2 , 값3 )
    - Map: key와value한쌍(entry)으로 여러쌍을 하나의 자료에 저장
        -> { 속성명:값1 , 속성명:값2 , 속성명:값3 }
    * List< Map<Object,String> >
    * [ { } , { } , { }  ]
    
    WebClient 객체 : 스프링에서 외부 API 요청 라이브러리
    1. 설치 : implementation 'org.springframework.boot:spring-boot-starter-webflux'
    2. 객체 : WebClient webClient = WebClient.builder().build();
    클래스명.class : 리플렉션( 특정/해당/ 클래스정보 반환)

*/
