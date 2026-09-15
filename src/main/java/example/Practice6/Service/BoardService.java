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

import example.Practice6.Dto.BoardDto;
import example.Practice6.Dto.CommentDto;
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
        boardEntities.forEach( (boardEntity) -> { // boardEntities.forEach = (리스트)한테 "forEach"라는 기능을 실행
        // (boardEntity) -> { ... } 람다식 , (boardEntity): 리스트에서 하나씩 꺼낸 원소를 boardEntity라는 이름으로 부름
        // -> { ... }: 꺼낼 때마다 이 작업을 실행
        // 1) List<BoardEntity> boardEntities = boardRepository.findAll(); 전체 게시글이 3개면 boardEntities에는 BoardEntity 객체3개가있음
        // 2) boardEntities.forEach하면 1번째 게시글을 boardEntity에 넣고 -> { ... } 실행
        // 3) BoardDto boardDto = BoardDto.from(boardEntity); 여기서 BoardDto.from(boardEntity) 실행하는데 
        // 3-1) boardEntity에는 1번째 게시글객체가 담겨있고 이 1번째 게시글객체를 매개변수로 담고 from() 호출
        // 3-2) 거기서 게시글번호 , 작성자 , 비밀번호 , 내용 , 생성시간 , 수정시간을 담아서 BoardDto 객체로 변환해서 리턴
        // 3-3) 돌아와서 BoardDto boardDto여기에 넣음
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
