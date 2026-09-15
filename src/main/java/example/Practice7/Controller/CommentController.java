package example.Practice7.Controller;

import example.Practice7.BaseTime;
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
public class CommentController extends BaseTime{
    @Id // PK(기본키)
    @GeneratedValue ( strategy = GenerationType.IDENTITY ) // MYSQL의 AUTO_INCREMENT와 같은 개념
    private Integer id; // 필드선언
    private String author; // 필드선언
    private String password; // 필드선언
    private String content; // 필드선언

    @ManyToOne
    @JoinColumn ( name = "board_id")
    

}
