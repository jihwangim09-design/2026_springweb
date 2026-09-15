package example.Practice7.model.Entity;

import example.Practice5.dto.BoardDto;
import example.Practice5.model.entity.BoardEntity;
import example.Practice7.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @Table (name="comment")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class CommentEntity extends BaseTime{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String password;
    private String content;
    
    @ManyToOne 
    @JoinColumn ( name =  "board_id")
    private BoardEntity boardEntity;
}
