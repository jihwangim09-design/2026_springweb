package example.Practice6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import example.Practice5.Service.CommentService;

@RestController 
public class CommentController {
    @Autowired private CommentService commentService;



    
}
