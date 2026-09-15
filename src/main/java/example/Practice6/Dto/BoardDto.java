package example.Practice6.Dto;

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

    public BoardEntity toEntity(){ // 게시물 등록 사용자가 입력한 것만 (author, password, content)
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

    public static BoardDto from(BoardEntity entity){ // entity는 Service에서 넘긴 boardEntity(게시물객체 1개)임 아직까진 Boardentity타입
        // 댓글 조회 DB에 있는 전체 정보 (id, 작성일 등 포함)
        return BoardDto.builder() 
                .id( entity.getId() )
                .author( entity.getAuthor() )
                .password( entity.getPassword() ) 
                .content( entity.getContent() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                .build();
        // entity 즉 boardEntity(게시물객체 1개)안에 있는 내용들을 새롭게 BoardDto 타입의 이름없는 객체로 생성
    }
}
