package example.Practice6.Dto;
import java.time.LocalDateTime;

import example.Practice6.model.Entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentDto {
    private Integer id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer boardId; // 사용자가 댓글을 등록할 때, "어느 게시글에 달 건지" 번호로 알려줘야 함
    // CommentEntity 처럼 안에 BoardEntity boardEntity로 할 수 없음 Entity안에 Dto가 들어가면 안됨

    public CommentEntity toEntity(){ // 댓글 등록 사용자가 입력한 것만 (author, password, content)
        return CommentEntity.builder()
                .author(this.author)
                .password(this.password)
                .content(this.content)
                .build();
    }
    
    public static CommentDto from(CommentEntity entity){ // 댓글 조회 DB에 있는 전체 정보 (id, 작성일 등 포함)
        return CommentDto.builder()
                .id( entity.getId() )
                .author( entity.getAuthor() )
                .password( entity.getPassword() )
                .content( entity.getContent() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                .build();
    }
}
