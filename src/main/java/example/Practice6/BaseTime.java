package example.Practice6;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@MappedSuperclass 
@EntityListeners ( AuditingEntityListener.class )
public class BaseTime {
    @CreatedDate private LocalDateTime createdAt;
    @LastModifiedBy private LocalDateTime updatedAt;
}
