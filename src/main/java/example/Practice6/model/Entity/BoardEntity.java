package example.Practice6.model.Entity;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import example.Practice5.dto.CommentDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Table ( name = "board")
@NoArgsConstructor @AllArgsConstructor @Data @Builder 
public class BoardEntity {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer boardId;
    private String author;
    private String password;
    private String content;

    
    @OneToMany ( mappedBy = "board_id" , cascade = CascadeType.ALL )
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

}