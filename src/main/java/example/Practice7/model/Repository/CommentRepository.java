package example.Practice7.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.Practice6.model.Entity.CommentEntity;



public interface CommentRepository extends JpaRepository< CommentEntity , Integer >{


    
}
