package example.day09.model.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table (name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class ApiEntity {
    // INSERT INTO board (idx, subject, name, regdate, content) VALUES
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String name;
    private String regdate;
    private String content;

    
}
