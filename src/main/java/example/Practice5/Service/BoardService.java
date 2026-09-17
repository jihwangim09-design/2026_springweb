package example.Practice5.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice5.dto.BoardDto;
import example.Practice5.dto.CommentDto;
import example.Practice5.model.Repository.BoardRepository;
import example.Practice5.model.entity.BoardEntity;

@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    // 1. 등록
    public boolean 게시물등록( BoardDto boardDto ){
        BoardEntity boardEntity = boardDto.toEntity(); // DTO를 ENTITY로 바꿈
        BoardEntity savedEntity = boardRepository.save( boardEntity ); // ENTITY SAVE
        if( savedEntity.getBoardId() >= 1 ) return true; // PK가 존재하면 true
        return false;
    }

    // 2. 전체조회
    public List<BoardDto> 게시물전체조회(){
        // 1. findAll 전체조회 findAll
        List<BoardEntity> boardEntities = boardRepository.findAll();
        // 모든 entity -> dto 변환 , 여러번(반복)
        // 2. 전체조회 Entity
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntities.forEach( (boardEntity ) -> {
            BoardDto boardDto = BoardDto.from(boardEntity); // entity -> dto
            // *** 게시글에 달린 댓글 목록 채우기 ***
            boardEntity.getCommentEntities().forEach((commentEntity) -> {
            CommentDto commentDto = CommentDto.from(commentEntity);
            boardDto.getComments().add(commentDto);
            });
            boardDtos.add(boardDto); // 변환된 dto를 리스트에 저장
        }); 
        return boardDtos;
    }

    // 3. 게시물 삭제
    public boolean 게시물삭제( Integer boardid , String password){
        Optional<BoardEntity> optional = boardRepository.findById(boardid);


    }
}
