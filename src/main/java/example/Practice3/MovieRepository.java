package example.Practice3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day09.model.Entity.ApiEntity;


 	
@Repository
public interface MovieRepository extends JpaRepository <ApiEntity,Integer> {
    

    
}
