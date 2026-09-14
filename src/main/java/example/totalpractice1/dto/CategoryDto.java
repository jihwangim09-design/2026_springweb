package example.totalpractice1.dto;

import java.util.ArrayList;
import java.util.List;

import example.day06.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Builder 
@Data 
public class CategoryDto {
    private Integer cno;
    private String name;
    @Builder.Default
    private List<ReviewDto> reviewDto = new ArrayList<>();

    public CategoryEntity toEntity(){
        return CategoryEntity.builder()
        .name(this.name)
        .build();
    }

    public static CategoryDto from(CategoryEntity categoryEntity){
        return CategoryDto.builder()
        .cno(categoryEntity.getCno())
        .name(categoryEntity.getName())
        .build();
    }
}
