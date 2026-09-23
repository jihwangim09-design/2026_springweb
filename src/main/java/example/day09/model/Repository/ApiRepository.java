package example.day09.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ApiRepository extends JpaRepository  {
    
}
