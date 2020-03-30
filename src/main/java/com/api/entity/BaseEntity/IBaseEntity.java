package com.api.entity.BaseEntity;

import java.io.Serializable;

public interface IBaseEntity<PK extends Serializable> extends Serializable {
    PK getId();
    void setId(PK id);
}
