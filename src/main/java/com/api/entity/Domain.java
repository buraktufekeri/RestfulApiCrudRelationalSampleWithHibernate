package com.api.entity;

import com.api.entity.BaseEntity.AbstractBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "domain")
public class Domain extends AbstractBaseEntity<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "gmy")
    private String gmy;
}
