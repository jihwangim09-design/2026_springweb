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
        CommentEntity commentEntity = CommentDto.toEntity();
        
    }
    
}
