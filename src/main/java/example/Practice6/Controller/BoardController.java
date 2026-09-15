package example.Practice6.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice6.Service.BoardService;
import example.Practice6.Dto.BoardDto;



@RestController // 웹 요청을 받아서 JSON으로 응답하는 역할을 하는 클래스
public class BoardController {
    @Autowired BoardService boardService; //@Autowired 변수에 들어갈 객체를 내가 직접 new로 만들지 않고, 스프링이 알아서 만들어서 넣어달라
    // BoardService 타입 객체

    @PostMapping ("/api/board") // POST 방식으로, /api/board라는 주소에 요청이 들어오면, 바로 아래 있는 메서드를 실행
    public boolean 게시물등록( @RequestBody BoardDto boardDto){ // @RequestBody가 사용자가 보낸 JSON을 BoardDto 객체로 변환해서 boardDto에 담음
        return boardService.게시물등록( boardDto ); // Service에서 true를 반환하면 boardService.게시물등록( boardDto ); 이게 true로 바뀜
        // 그래서 JSON형태로 true로 반환을 함
    }
    
    @GetMapping("/api/board")
    public List<BoardDto> 게시물전체조회(){ // 그냥 다보여줘라서 넘길 파라미터가 없음
        return boardService.게시물전체조회();
    }

    @DeleteMapping("/api/board") // 사용자가 DELETE http://localhost:8080/api/board?id=3&password=1234 를 요청하면 
    public boolean 게시물삭제( // @RequestParam 이 URL에서 값을 꺼내서 Controller가 id=3, password="1234"을 갖게됨
    @RequestParam ( name = "id") Integer id, // name = "id": "URL의 ? 뒤에서, id라는 이름의 값을 찾아서 꺼내줘
    @RequestParam ( name = "password") String password ) // name = "password": "URL의 ? 뒤에서, password라는 이름의 값을 찾아서 꺼내줘
    {
        return boardService.게시물삭제( id , password );
    }
}
