package example.totalpractice1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.totalpractice1.Service.ReviewsService;
import example.totalpractice1.dto.reviewsDto;

@RestController 
@RequestMapping("/api/reviews")
@CrossOrigin(value = "http://localhost:5173")

public class ReviewsController {
    @Autowired ReviewsService reviewsService;

    @GetMapping
    public List<reviewsDto> getreviews(@RequestParam Integer bno){
        return ReviewsService.getreviewsByBno(bno);
    }

    @PostMapping 
    public boolean createreview( @RequestBody reviewsDto reviewsDto){
        return ReviewsService.createreview( reviewsDto );
    }

    @DeleteMapping
    public boolean deletereview(
    @RequestParam ( name = "rno" ) Integer rno){
        return reviewsService.deletereview( rno );
    }
}
