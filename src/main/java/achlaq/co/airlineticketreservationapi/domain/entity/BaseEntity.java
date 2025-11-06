package achlaq.co.airlineticketreservationapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "created_by", updatable = false, length = 50)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_by", length = 50)
    private String deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    public void markDeleted(String username) {
        this.deletedBy = username;
        this.deletedDate = LocalDateTime.now();
    }

    public void restore() {
        this.deletedBy = null;
        this.deletedDate = null;
    }

    public void setCreatedByUser(String username) {
        this.createdBy = username;
    }

    public void setUpdatedByUser(String username) {
        this.updatedBy = username;
    }
}
