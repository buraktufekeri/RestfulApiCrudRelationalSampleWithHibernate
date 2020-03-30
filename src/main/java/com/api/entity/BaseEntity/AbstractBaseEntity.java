package com.api.entity.BaseEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractBaseEntity<PK extends Serializable> implements IBaseEntity<PK> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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
        return getClass().getName() + "[" + id + "]";
    }
}
