package example.Practice6.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.Practice6.model.Entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class BoardDto {
    private Integer id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity toEntity(){
        return BoardEntity.builder() // .build()가 실행되면 객체가 생성
        // this는 이 메서드를 실행하고 있는 바로 그 객체 자신
        // 여기서는 boardDto.toEntity()를 통해 toEntity()를 실행했으므로 
        // .author(this.author)는 boardDto 객체가 가지고 있던 author를 가리킨다
        // 이 객체는 boardDto에서 온 객체를 가지고있음
                .author(this.author)
                .password(this.password)
                .content(this.content)
                .build();
    }

    public static BoardDto from(BoardEntity entity){
        return BoardDto.builder()
                .id( entity.getId() )
                .author( entity.getAuthor() )
                .password( entity.getPassword() ) 
                .content( entity.getContent() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                .build();

    }
}
