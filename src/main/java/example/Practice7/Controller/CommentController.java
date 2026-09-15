package example.Practice7.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice7.Service.CommentService;
import example.Practice7.dto.CommentDto;

@RestController 
public class CommentController{
    @Autowired CommentService commentService;

    @PostMapping ("/api/comment")
    public boolean 댓글등록( @RequestBody CommentDto commentDto){
        return commentService.댓글등록( commentDto );
    }

    @DeleteMapping ("/api/comment")
    public boolean 댓글삭제( 
        @RequestParam ( name = "id") Integer id,
        @RequestParam ( name = "password") String password 
    ){
        return commentService.댓글삭제(id, password);
    }
}
