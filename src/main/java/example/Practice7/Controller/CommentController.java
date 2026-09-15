package example.Practice7.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice6.Service.CommentService;
import example.Practice6.dto.CommentDto;


@RestController 
public class CommentController {
    @Autowired CommentService commentService;

    @PostMapping ("/api/board/comments")
    public boolean 댓글등록( @RequestBody CommentDto commentDTo){
        return commentService.댓글등록( commentDTo );

    }
    @DeleteMapping ("/api/board/comments")
    public boolean 댓글삭제(
        @RequestParam ( name = "commentId") Integer commentId,
        @RequestParam ( name = "password") String password) {
        return commentService.댓글삭제( commentId , password );
    }
    
}
