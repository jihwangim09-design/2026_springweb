package example.totalpractice1.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpractice1.dto.ReviewDto;
import example.totalpractice1.model.Entity.ProductsEntity;
import example.totalpractice1.model.Entity.ReviewEntity;
import example.totalpractice1.model.Entity.ReviewEntity;
import example.totalpractice1.model.Repository.ReviewRepository;

@Service 
public class ReviewService {
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private ProductRepository productRepository;



    public List<ReviewDto> getreviews(Integer bno) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByProductsEntity_Pno(bno);
        List<ReviewDto> reviewDtos = new ArrayList<>();

        reviewEntities.forEach((entity) -> {
            reviewDtos.add(ReviewDto.from(entity));
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
    






} 
