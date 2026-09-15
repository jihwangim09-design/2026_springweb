package example.Practice6;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor 
@EntityListeners ( AuditingEntityListener.class )
public class BaseTime {
    @CreatedDate private LocalDateTime createdAt;
    @LastModifiedDate private LocalDateTime updatedAt;
}
