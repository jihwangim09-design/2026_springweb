package example.Practice6.model.Entity;

import example.Practice6.BaseTime;
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

@Entity // JPA가 관리하는 진짜 DB 테이블용 클래스
@Table ( name = "comment") // 이 클래스는 DB의 comment라는 이름의 테이블이랑 연결
@Data @NoArgsConstructor @AllArgsConstructor @Builder // Lombok , Lombok은 반복적으로 써야 하는 코드(getter, setter, 생성자 등)를 자동으로 만들어주는 도구
public class CommentEntity extends BaseTime{
    @Id // PK(기본키)
    @GeneratedValue ( strategy = GenerationType.IDENTITY ) // MYSQL의 AUTO_INCREMENT와 같은 개념
    private Integer id; // 필드선언
    private String author; // 필드선언
    private String password; // 필드선언
    private String content; // 필드선언
    
    @ManyToOne 
    // 여러 개(Many)의 댓글이, 하나(One)의 게시글에 속한다
    @JoinColumn ( name = "board_id")
    // 이 관계를 DB에서 실제로 어떻게 저장하냐
    // comment 테이블 안에 board_id라는 컬럼을 만들어서, 거기에 "이 댓글이 몇 번 게시글에 속하는지를 저장
    private BoardEntity boardEntity;
    // 어느 게시글 번호(숫자)에 속하는지"가 아니라, "어느 게시글 객체(전체)에 속하는지" 를 가지고 있음
    // Integer boardId 대신 BoardEntity boardEntity로 하는 이유는 
    // JPA의 연관관계는 "숫자 하나로 연결"하는 게 아니라, "객체와 객체를 직접 연결"하는 방식

}
