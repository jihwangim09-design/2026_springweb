package example.day09.model.Dto;

import java.time.LocalDateTime;

import example.day09.model.Entity.ApiEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class ApiDto {
    private Integer id;
    private String subject;
    private String name;
    private String regdate;
    private String content;

    public ApiEntity ToEntity(){
        return ApiEntity.builder()
        .name(name).subject(subject).content(content).regdate(LocalDateTime.now().toString())
        .build();

    }

    public static ApiDto from(ApiEntity apiEntity){
        return ApiDto.builder()
        .name(apiEntity.getName())
        .subject(apiEntity.getSubject())
        .content(apiEntity.getContent())
        .regdate(apiEntity.getRegdate())
        .build();
    }
}
