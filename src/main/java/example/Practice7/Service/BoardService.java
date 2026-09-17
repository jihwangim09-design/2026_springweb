package example.Practice7.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice7.dto.BoardDto;
import example.Practice7.dto.CommentDto;
import example.Practice7.model.Entity.BoardEntity;
import example.Practice7.model.Entity.CommentEntity;
import example.Practice7.model.Repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired BoardRepository boardRepository;

    public boolean 게시물등록( BoardDto boardDto ){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity savedEntity = boardRepository.save(boardEntity);
        if ( savedEntity.getId() >= 1 ) return true;
        return false;
    }

    public List<BoardDto> 게시물전체조회(){
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntities.forEach( (boardentity) -> {
            BoardDto boardDto = BoardDto.from(boardentity);
            boardentity.getCommentEntities().forEach( (commententity) -> {
            CommentDto commentDto = CommentDto.from(commententity);
            boardDto.getComments().add(commentDto);
            });
            boardDtos.add(boardDto);
        });
        return boardDtos;
    }

    public boolean 게시물삭제( Integer boardid , String password){
        Optional<BoardEntity> optional = boardRepository.findById(boardid)

        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            if(boardEntity.getPassword().equals(password)){
                boardRepository.deleteById(boardid);
                return true;
            }
            return false;
        }
    }

}
