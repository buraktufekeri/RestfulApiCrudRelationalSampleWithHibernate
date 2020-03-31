package com.api.entity;

import com.api.entity.BaseEntity.AbstractBaseEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "domain_features")
public class DomainFeature extends AbstractBaseEntity<Long> {

    @Column(name = "product_group")
    private String productGroup;
    @Column(name = "domain_architect")
    private String domainArchitect;
    @Column(name = "test_automation_agents")
    private String testAutomationAgents;
    @Column(name = "bank_functional_test_resource")
    private String bankFunctionalTestResource;
    @Column(name = "bank_automation_test_resource")
    private String bankAutomationTestResource;
    @Column(name = "softtech_test_automation_friendly")
    private String softtechTestAutomationFriendly;
    @Column(name = "location")
    private String location;
    @Column(name = "scope_analysis_study")
    private String scopeAnalysisStudy;
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
