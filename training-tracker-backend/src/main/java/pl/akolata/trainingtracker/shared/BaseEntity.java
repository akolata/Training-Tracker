package pl.akolata.trainingtracker.shared;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.FIELD)
@ToString
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Getter
    private Long id;

    @Version
    @Getter
    private Timestamp version;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private LocalDateTime updatedAt;

    @Column(length = 36, nullable = false, updatable = false)
    @Getter
    private String uuid = UUID.randomUUID().toString();

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object that) {
        return this == that || that instanceof BaseEntity
                && Objects.equals(uuid, ((BaseEntity) that).uuid);
    }
}