package example.day09.model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table (name = "api")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class ApiEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

}
