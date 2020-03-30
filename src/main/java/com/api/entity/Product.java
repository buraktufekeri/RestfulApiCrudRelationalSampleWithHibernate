package com.api.entity;

import com.api.entity.BaseEntity.AbstractBaseEntityAudit;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends AbstractBaseEntityAudit<Long> {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "quantity", nullable = false)
    private double quantity;
    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
