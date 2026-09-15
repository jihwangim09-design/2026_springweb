package example.Practice6.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice6.dto.CommentDto;
import example.Practice6.model.Entity.BoardEntity;
import example.Practice6.model.Entity.CommentEntity;
import example.Practice6.model.Repository.BoardRepository;
import example.Practice6.model.Repository.CommentRepository;
import jakarta.websocket.server.ServerEndpoint;

@Service 
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardRepository boardRepository;

    public boolean 댓글등록( CommentDto commentDto ){
    CommentEntity commentEntity = commentDto.toEntity();
    BoardEntity boardEntity = boardRepository.findById( commentDto.getBoardId() ).orElse(null);
    commentEntity.setBoardEntity( boardEntity );
    CommentEntity savedEntity = commentRepository.save( commentEntity );
    if ( savedEntity.getId() >= 1 ) return true;
    return false;
}

    public boolean 댓글삭제 ( Integer commentId , String password){
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse( null );
        if ( commentEntity != null ){
            if( commentEntity.getPassword().equals(password)){
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }
}
