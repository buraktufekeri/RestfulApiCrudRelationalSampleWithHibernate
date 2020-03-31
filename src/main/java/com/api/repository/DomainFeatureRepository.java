package com.api.repository;

import com.api.entity.DomainFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainFeatureRepository extends JpaRepository<DomainFeature, Long> {
}
