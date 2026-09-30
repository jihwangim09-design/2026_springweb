package example.Practice5.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.Practice5.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 즉 DTO의 역할은 엔티티 자료들을 프론트엔드로 전달
// + 추가적인 자료들을 무엇 주고 받을지 RESTAPI 명세서에 적혀있음
@NoArgsConstructor @AllArgsConstructor @Data @Builder 
public class BoardDto {
    private Integer boardId;
    private String author;
    private String password;
    private String content;
    // + BASETIME
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // + 댓글 목록 //
    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();
    // 
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .author(this.author) // private String author;
                .password(this.password) // private String password;
                .content(this.content) // private String content;
                .build();
    }
    //
   public static BoardDto from( BoardEntity entity ){
        return BoardDto.builder()
                .boardId( entity.getId() ) // private Integer boardId;
                .author( entity.getAuthor() )
                .content( entity.getContent() )
                .password( entity.getPassword() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                .build();
    }
}
 