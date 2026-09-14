package example.totalpractice1.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice1.model.Entity.ReviewsEntity;

@Repository 
public interface ReviewsRepository extends JpaRepository <ReviewsEntity , Integer > {

    
}
