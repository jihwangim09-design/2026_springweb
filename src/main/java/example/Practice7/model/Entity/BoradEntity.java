package example.Practice7.model.Entity;

import example.Practice3.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table (name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoradEntity extends BaseTime{
    @Id
    @GeneratedValue (strategy = GenertionType.IDENTITY)
    private Integer id;
    private String author;
    private String password;
    private String content;

    @OneToMany ( mappedBy = "")

}

