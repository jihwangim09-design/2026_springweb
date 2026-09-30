package example.Practice7.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice7.dto.CommentDto;
import example.Practice7.model.Entity.CommentEntity;
import example.Practice7.model.Repository.BoardRepository;
import example.Practice7.model.Repository.CommentRepository;

@Service 
public class CommentService {
    @Autowired CommentRepository commentRepository;
    @Autowired BoardRepository boardRepository;

    public boolean 댓글등록 ( CommentDto commentDto){
        CommentEntity commentEntity = commentDto.toEntity(); // dto -> entity (인스턴스 메소드라 소문자 commentDto 로 호출)
        // TODO: 게시글(boardRepository)로 boardEntity 조회 후 commentEntity 에 연결하고 저장하는 로직 작성
        return false;
    }

    // TODO: 컨트롤러가 호출하는 댓글삭제 — 아직 미구현이라 컴파일용 껍데기만 둠
    public boolean 댓글삭제 ( Integer commentid , String password ){
        return false;
    }

}
