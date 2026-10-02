package example.day13;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.server.ExportException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

public class FileService {
    
    // [1] 업로드 경로 설정
    // 1. 현재 프로젝트의 최상위 폴더찾기
    private String baseDir = System.getProperty("user.dir");
    // 2.
    // src폴더 : 실행전(서버에 업로드전) 폴더로 개발자가 코드 작성하는 폴더
    // build폴더 : 실행후(서버에 업로드된) 폴더로 개발자가 작성한 코드 실행(컴파일)한 결과물 폴더
    // * 일반사용자들은 업로드할 경우 개발자폴더(src) 가 아닌 서버폴더(build)에 업로드 해야한다.
    // * 추후에 AWS(클라우드) 경우 에는 클라우드 IP
    private String uploadPath = baseDir+"/build/resources/main/static/upload";
    
    // [2] 업로드 함수
    public String fildUpload(MultipartFile multipartFile){
        // 1. 업로드할 파일의 MultipartFile 인터페이스 가져오기
        // 2. 만약에 업로드 파일이 없으면 취소
        if( multipartFile == null || multipartFile.isEmpty()) { return  null;}
        // 3. 만약에 업로드 폴더가 없으면 폴더 생성 , File객체란? 자바가 운영체제의 파일 조작 클래스 
        File dir = new File( uploadPath ); // 설정한 경로 File 객체에 대입한다. 
        if ( !dir.exists() ){ dir.mkdir(); } // 설정한 경로에 폴더가 없으면 폴더 생성
        // 4. 업로드할 파일명이 중복 방지 --> 1] UUID 2] 업로드날짜/시간 3] PK 등등 식별 추가한다.
        // 왜? 유재석/강호동이 서로 다른 파일의 같은 파일명으로 짱구.jpg 업로드 한 경우에 다른 파일 취급하기 위해서
        // 예] 짱_구.jpg --> uuid _ 짱-구.jpg
        // _역할은 uuid와 실제 파일명과 구분용도 , 파일명에 _언더바 존재하면 안된다.
        // replaceAll("기존문자" , "새로운문자") , 문자열내 기존문자들을 새로운문자로 치환/교환 함수
        String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename()
                                                                        .replaceAll("-", "-");

        // 5. 업로드 , .transferTo(업로드할 file객체); , 예외처리발생
        try{
            multipartFile.transferTo(new File( uploadPath+fileName));

        }catch(Exception e){System.out.println(e);};
        return null;

    }

    // [3] 다운로드 함수
    // C드라이브 파일 --FileInput --> JAVA -- Servletout --> 브라우저
    public void fileDownload(String fileName , HttpServletResponse response){
        // 1. 다운로드할 파일명과 HTTP응답객체 받는다.
        // 2. 다운로드할 파일명과 업로드 경로 조합
        String downloadPath = uploadPath + fileName; // 업로드경로 + 파일명;
        // 3. 만약에 해당 경로에 파일이 없으면
        File file = new File( downloadPath ); if( file.exists() ){ return; }
        // 4. 있으면 파일 읽어오기 , FileInputStream
        try{
            FileInputStream fin = new FileInputStream( downloadPath ); // 파일입력객체생성
            long fileSize = file.length(); // 파일명 (바이트) 용량확인
            byte[] bytes = new byte[ (int)fileSize ]; // 파일 용량만큼 바이트 배열 생성
            fin.read( bytes ); // 파일입력객체가 입력온 바이트들을 바이트배열에 저장
            fin.close(); // 스트림(이동)긴 안전하게 스트림 직접 닫기

        }catch( Exception e ){System.out.println(e);}

    }

    // [4] 파일 삭제 함수



} // service end
