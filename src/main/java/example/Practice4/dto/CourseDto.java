package example.Practice4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import example.Practice4.model.entity.CourseEntity;

 
 	
@NoArgsConstructor@AllArgsConstructor@Builder@Data
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // + 학생목록
    @Builder.Default
    private List<StudentDto> studentDtos = new ArrayList<>();
    // + toEntity : 과정등록 용도
    public CourseEntity toEntity( ){
        return CourseEntity.builder() // → 빈 빌더 생성 밑에서 .build();로 완성된 객체 리턴
                .courseName(this.courseName) // courseDto가 { courseName: "자바과정" } 이라는 값을 갖고 있었다면 "자바과정"을 꺼내옴
                .build(); // CourseEntity 객체완성
    }
    // + from : 출력 용도
    public static CourseDto from( CourseEntity entity ){
        return CourseDto.builder()
                .courseId( entity.getCourseId() )
                .courseName( entity.getCourseName() )
                .createdAt( entity.getCreatedAt() )
                .updatedAt( entity.getUpdatedAt() )
                // 학생목록은 서비스계층 에서~
                .build();
    }
}
