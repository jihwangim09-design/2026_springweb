package example.Practice7.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice7.dto.BoardDto;
import example.Practice7.dto.CommentDto;
import example.Practice7.model.Entity.BoardEntity;
import example.Practice7.model.Repository.BoardRepository;

@Service
public class BoardService {
    @Autowired BoardRepository boardRepository;

  
    public boolean 게시물등록 (BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        Board
    }

}
