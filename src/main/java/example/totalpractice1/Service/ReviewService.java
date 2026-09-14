package example.totalpractice1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpractice1.dto.ReviewDto;
import example.totalpractice1.model.Entity.ReviewEntity;
import example.totalpractice1.model.Entity.ProductsEntity;
import example.totalpractice1.model.Repository.ReviewRepository;
import example.totalpractice1.model.Repository.ProductsRepository;

@Service
public class ReviewService {

    @Autowired private ReviewRepository reviewRepository;
    @Autowired private ProductsRepository productsRepository;

    public List<ReviewDto> getreviews(Integer bno){
    List<ReviewEntity> reviewEntities = reviewRepository.findAll();
    List<ReviewDto> reviewDtos = new ArrayList<>();
        reviewEntities.forEach((reviewEntity) -> {
            if (reviewEntity.getProductEntity().getBno().equals(bno)) {
                ReviewDto reviewDto = ReviewDto.from(reviewEntity);
                reviewDtos.add(reviewDto);
            }
    });
    return reviewDtos;
}

    public boolean createreview(ReviewDto reviewDto) {
        ProductsEntity productEntity = productsRepository.findById(reviewDto.getBno()).orElse(null);
        if (productEntity == null) return false;

        ReviewEntity reviewEntity = reviewDto.toEntity(productEntity);
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);

        if (savedEntity.getRno() >= 1) return true;
        return false;
    }

    public boolean deletereview(Integer rno) {
        reviewRepository.deleteById(rno);
        return true;
    }
}