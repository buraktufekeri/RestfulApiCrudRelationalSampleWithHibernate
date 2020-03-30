package com.api.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntityAudit<PK extends Serializable> extends AbstractBaseEntity<PK> {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    @PrePersist
    public void setCreationDate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    public void setChangeDate(){
        this.updatedAt = new Date();
    }
}
