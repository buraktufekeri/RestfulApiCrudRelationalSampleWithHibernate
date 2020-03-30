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
@Table(name = "category")
public class Category extends AbstractBaseEntity<Long> {

    @Column(name = "name")
    private String name;
/*
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();*/
}
