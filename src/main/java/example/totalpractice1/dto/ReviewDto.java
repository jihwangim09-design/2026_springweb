package example.totalpractice1.dto;

import example.totalpractice1.model.Entity.ProductsEntity;
import example.totalpractice1.model.Entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class ReviewDto {
    private Integer rno;
    private Integer bno;
    private String reviewer;
    private String content;
    private int rating;

    public static ReviewDto from(ReviewEntity entity) {
        return ReviewDto.builder()
                .rno(entity.getRno())
                .bno(entity.getProductEntity().getPno())
                .reviewer(entity.getReviewer())
                .content(entity.getContent())
                .rating(entity.getRating())
                .build();
    }

    public ReviewEntity toEntity(ProductsEntity productEntity) {
        return ReviewEntity.builder()
                .reviewer(this.reviewer)
                .content(this.content)
                .rating(this.rating)
                .productEntity(productEntity)
                .build();
    }
}
