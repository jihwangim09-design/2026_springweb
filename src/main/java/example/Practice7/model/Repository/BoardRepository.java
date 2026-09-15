package example.Practice7.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.Practice7.model.Entity.BoardEntity;



@Repository 
public interface BoardRepository extends JpaRepository< BoardEntity, Integer > {


}
 