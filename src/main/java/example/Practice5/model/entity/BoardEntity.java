package example.Practice5.model.entity;

import java.util.ArrayList;
import java.util.List;

import example.Practice5.Basetime;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
// 즉 엔티티란? 영속석의 실체 , 데이터베이스 표
// 다르면 연동/매핑이 안된다

@Entity@Table ( name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
@Getter @Setter 
public class BoardEntity extends Basetime {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer Id;
    // @Column
    // author, password, content는 옵션 없이 쓸 거면 @Column 생략 가능
    private String author;
    private String password;
    private String content;

    // 한 게시글에는 여러 댓글 등록 가능
    // mappedBy = "boardEntity"FK는  CommentEntity의 boardEntity 필드가 갖고 있음
    // 양방향
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL) 
    @ToString.Exclude // 양방환순환참조방지
    @Builder.Default //  
    // 댓글(CommentEntity)을 여러 개 담을 리스트
    private List<CommentEntity> commentEntities = new ArrayList<>();// 여기서 new ArrayList<>() 이게 Default?
    // 빌더 패턴 사용시에는 new ArrayList<>(); 가 현재 초기값을 Default로 하겠다?
    // 생성자는 메서드라서 매개변수 위치를 지켜야하는데 빌더를 쓰면 순서 상관없이 유연하다.
}
