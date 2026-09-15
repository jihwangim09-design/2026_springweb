package example.Practice6.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import example.Practice6.dto.BoardDto;
import example.Practice6.dto.CommentDto;
import example.Practice6.model.Entity.BoardEntity;
import example.Practice6.model.Repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;


    public boolean 게시물등록( BoardDto boardDto ){
        BoardEntity boardEntity = boardDto.toEntity(); // dto --> entity 
        // public interface BoardRepository extends JpaRepository<BoardEntity, Integer>
        // BoardEntity 타입만 다룬다고 명시했기에 dto --> entity 를 해줘야함
        BoardEntity savedEntity = boardRepository.save( boardEntity ); // entity save 
        if( savedEntity.getId() >= 1 ) return true; // pk가 존재하면 성공 
        return false;
    }

    public List<BoardDto> 게시물전체조회(){
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntities.forEach( (boardEntity) -> { 
            BoardDto boardDto = BoardDto.from(boardEntity);
            boardEntity.getCommentEntities().forEach((commentEntity)-> {
            CommentDto commentDto = CommentDto.from( commentEntity );
            boardDto.getComments().add(commentDto);
            });
            boardDtos.add(boardDto);
        });
        return boardDtos;
    }

    public boolean 게시물삭제( Integer id , String password ) {
        BoardEntity boardEntity = boardRepository.findById(id).orElse( null );
        if(boardEntity != null){
            if (boardEntity.getPassword().equals( password) ){
                boardRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
    



}
