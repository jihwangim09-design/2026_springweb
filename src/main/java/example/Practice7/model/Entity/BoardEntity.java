package example.Practice7.model.Entity;

import java.util.ArrayList;
import java.util.List;

import example.Practice7.BaseTime;
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

@Entity @Table (name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardEntity extends BaseTime{
    @Id 
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String password;
    private String content;

    @OneToMany (mappedBy = "boardEntity" , cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities = new ArrayList<>();

}
