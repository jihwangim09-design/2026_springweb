package example.Practice6.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.Practice5.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder 
public class BoardDto {
    private Integer boardId;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CommentDto> commnets = new ArrayList<>();

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content)
                .build();
    }

    public static BoardDto from(BoardEntity entity ){
        return BoardDto.builder()
                .boardId( entity.getBoardId() )
                .author( entity.getAuthor() )
                .password( entity.getPassword() )
                .content( entity.getContent() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                .build();
    }

}
