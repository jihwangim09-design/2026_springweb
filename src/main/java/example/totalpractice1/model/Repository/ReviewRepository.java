package example.totalpractice1.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice1.model.Entity.ReviewEntity;

@Repository 
public interface ReviewRepository extends JpaRepository <ReviewEntity , Integer > {

}
