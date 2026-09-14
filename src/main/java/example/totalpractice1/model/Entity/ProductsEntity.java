package example.totalpractice1.model.Entity;

import example.day06.CategoryEntity;
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

@Entity 
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Builder 
@Table (name = "product")
public class ProductsEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer bno;

    private String name;
    private Integer price;

    @JoinColumn (name = "cno")
    @ManyToOne 
    private CategoryEntity categoryEntity;
}
