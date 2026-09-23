package example.day09.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day09.model.Entity.ApiEntity;

@Repository 
public interface ApiRepository extends JpaRepository<ApiEntity , Integer>  {
    
}
