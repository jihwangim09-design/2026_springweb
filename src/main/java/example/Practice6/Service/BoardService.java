package example.Practice6.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // 방금 만든 이 boardEntity를 DB에 저장 save()는 BoardRepository를 만들 때 JpaRepository를 상속받아서 자동으로 딸려온 기능
        // save()안에서는 
        // INSERT INTO board (author, password, content, created_at, updated_at) 
        // VALUES ('유재석', '1234', '안녕', NOW(), NOW()); 이런 SQL을 자동으로 생성 DB가 이행을 저장하면서 PK(Id)를 하나 부여함 AUTO_INCREMENT로 인해서
        // savedEntity에는 id까지 채워지고 저장완료된 객체를 받음
        if( savedEntity.getId() >= 1 ) return true; // ex)3번째 게시글이면 Id가 3임 
        // AUTO_INCREMENT는 항상 1부터시작해서 1이상이면 DB가 번호를 부여했다는 뜻
        return false;
        // 실패시 false 반환
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
            // boardEntity는 위에서 생긴 1개게시물객체를 담고 있음
            // boardEntity.getCommentEntities() 는 1개게시물객체 boardEntity에 연결된 List<CommentEntity>를 전부 가져옴
            CommentDto commentDto = CommentDto.from( commentEntity );
            // commentDto에 dto로 변환된 댓글이 있음
            boardDto.getComments().add(commentDto);
            // getComments()이건 빈 리스트 @Builder.Default 이거로 빈 리스트를 생성하게 해서 nullpoint에러가 안생김
            // 빈리스트를 가져와서 .add(commentDto); 로 dto로 변환한 댓글들을 Comments에 추가함
            });
            boardDtos.add(boardDto);
            // List<BoardDto> boardDtos = new ArrayList<>();위에서 만든 <BoardDto>타입의 리스트 boardDtos에 더함
        });
        return boardDtos;
        // 게시물이랑 댓글을 Dto로 바꾼 리스트를 반환
        // 댓글을 그냥 entity로 담고 있으면 서로가 서로를 참조하면서 무한 반복 사용자한테 나가는 데이터는 Dto로 통일해야하는게 관례?
    }

    public boolean 게시물삭제( Integer boardId , String password ) {
        Optional<BoardEntity> optional = boardRepository.findById(boardId); // Optional은 포장타입 BoardEntity도 타입
        // boardId(예: 3)를 갖고, boardRepository(DB 접근 도구)한테 3번 게시글을 찾아와라
        // Optional로 받는 이유 : 있을 수도 있고 없을 수도 있는 상태를 받는 그릇이기 때문
        // findById()로 DB를 조회할 때는 그 번호(3번)의 게시글이 진짜로 존재해서 찾아짐
        // 그 번호의 게시글이 없어서 (이미 삭제됐거나, 애초에 없는 번호거나) 못 찾음
        // findAll()은 다 가져와여서 결과가 없으면 그냥 빈 리스트([]) 반환하면 되지만 findById()는 딱 하나만 찾으라고
        // 요청하는 거라서 찾았다/못 찾았다"를 구분해서 표현해야 할 필요
        // BoardEntity boardEntity = boardRepository.findById(boardId); 일때 게시글이 없으면 boardEntity는 null이됌
        // 근데 이 상태에서 getPassword()를 하면 에러가 남 Optional은 자바가 이 값은 없을 수도 있으니 무작정 바로 쓰지 말고 반드시 확인부터 하고 쓰라고 강제로 상기시켜주는 장치
        if (optional.isPresent()){
        // optional.isPresent()은 optional안에 게시글이 들어있는지 확인 결과는 true or false로 반환
            BoardEntity boardEntity = optional.get(); // true면 optional 안에 들어있던 진짜 BoardEntity 객체를 꺼내서 boardEntity에 넣음 
            // BoardEntity 객체기 때문에 타입도 BoardEntity로 해놓음
            if (boardEntity.getPassword().equals(password)){  // 비밀번호 일치 확인
                boardRepository.deleteById(boardId); // 내부적으로 DELETE FROM board WHERE id = ... 같은 SQL이 자동으로 실행
                return true; // 삭제후 true 반환
            }
        }
        return false; // 삭제르 못했을시 false 반환
    } 

    /* 
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
    */
    



}
