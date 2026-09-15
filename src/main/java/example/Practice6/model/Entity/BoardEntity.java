package example.Practice6.model.Entity;

import java.util.ArrayList;
import java.util.List;

import example.Practice6.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // JPA가 관리하는 진짜 DB 테이블용 클래스
@Table ( name = "board") // 이 클래스는 DB의 board라는 이름의 테이블이랑 연결
@Data @NoArgsConstructor @AllArgsConstructor @Builder // Lombok , Lombok은 반복적으로 써야 하는 코드(getter, setter, 생성자 등)를 자동으로 만들어주는 도구
public class BoardEntity extends BaseTime {
    @Id // PK(기본키)
    @GeneratedValue ( strategy = GenerationType.IDENTITY ) // MYSQL의 AUTO_INCREMENT와 같은 개념
    private Integer id; // 필드선언
    private String author; // 필드선언
    private String password; // 필드선언
    private String content; // 필드선언

    @OneToMany ( mappedBy = "boardEntity" , cascade = CascadeType.ALL) 
    // 하나(One)의 게시글이, 여러 개(Many)의 댓글을 가질 수 있다 Board(1) ↔ Comment(N)
    // mappedBy = "boardEntity" 이 관계의 주인은 내가 아니라, CommentEntity 쪽에 있는 boardEntity라는 필드 CommentEntity.java 안에 private BoardEntity boardEntity;
    // cascade = CascadeType.ALL 이 게시글(Board)에 생기는 모든 변화(저장, 삭제 등)를, 케시물에 달린 댓글(Comment)들한테도 그대로 적용
    @ToString.Exclude // 서로 무한히 호출하는 문제 방지
    @Builder.Default // Builder.Default가 있으면 안 채워도 new ArrayList<>() (빈 리스트)가 기본으로 들어감
    private List<CommentEntity> commentEntities = new ArrayList<>();
    // List<CommentEntity> (댓글 여러 개 담는 리스트) 
}
