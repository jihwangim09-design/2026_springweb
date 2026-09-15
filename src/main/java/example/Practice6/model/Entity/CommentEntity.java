package example.Practice6.model.Entity;


import org.springframework.data.jpa.repository.Meta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table ( name = "comment")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class CommentEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Integer commentId;
    private String author;
    private String password;
    private String content;


    @ManyToOne 
    @JoinColumn ( name = "board_id")
    private BoardEntity boardEntity;


}
