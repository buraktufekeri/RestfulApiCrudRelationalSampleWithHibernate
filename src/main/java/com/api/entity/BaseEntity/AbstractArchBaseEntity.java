package com.api.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractArchBaseEntity<PK extends Serializable> implements IBaseEntity<PK>{

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractArchBaseEntity{" +
                "id=" + id +
                '}';
    }
}
