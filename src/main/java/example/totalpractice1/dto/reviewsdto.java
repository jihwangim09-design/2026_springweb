package example.totalpractice1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class reviewsDto {
    private Integer rno;
    private Integer bno;
    private String reviewer;
    private String content;
    private int rating;
}
