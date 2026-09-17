package example.Practice7.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice7.Service.BoardService;
import example.Practice7.dto.BoardDto;

@RestController 
public class BoardController {
    @Autowired BoardService boardService;

    @PostMapping ("/api/board")
    public boolean 게시물등록( @RequestBody BoardDto boardDto){
        return boardService.게시물등록( boardDto );
    }
    @GetMapping ("/api/board")
    public List<BoardDto> 게시물전체조회(){
        return boardService.게시물전체조회();
    }

    @DeleteMapping ("/api/board")
    public boolean 게시물삭제(
        @RequestParam ( name = "id" ) Integer id,
        @RequestParam ( name = "pasasword" ) String pasasword
    ){
        return boardService.게시물삭제(id, pasasword);
    }
}
