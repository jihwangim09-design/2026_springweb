package example.totalpractice1.dto;

import example.totalpractice1.model.Entity.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Data 
public class ProductResponseDto {
    private Integer bno;
    private String name;
    private Integer price;
    private Integer cno;
    private String cName;
    private String categoryname;

    public ProductsEntity toEntity() {
        return ProductsEntity.builder()
                .bno(this.bno)
                .name(this.name)
                .price(this.price)
                .build();
    }

    public static ProductResponseDto from(ProductsEntity entity) {
        return ProductResponseDto.builder()
                .bno(entity.getBno())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}