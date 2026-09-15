package example.Practice6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice6.Service.CommentService;
import example.Practice6.Dto.CommentDto;


@RestController 
public class CommentController {
    @Autowired CommentService commentService;

    @PostMapping ("/api/board/comments") // /api/comment라고 하면 댓글이 독립적인 존재처럼 보여서 게시글이랑 관련있는지 URL만 봐서 알기 어려줌
    public boolean 댓글등록( @RequestBody CommentDto commentDTo){
        return commentService.댓글등록( commentDTo );

    }
    @DeleteMapping ("/api/board/comments")
    public boolean 댓글삭제(
        @RequestParam ( name = "commentId") Integer commentId, // 게시글id 인지 댓글 id인지 구분이 안되서 comment라고 구분
        @RequestParam ( name = "password") String password) {
        return commentService.댓글삭제( commentId , password );
    }
    
}
