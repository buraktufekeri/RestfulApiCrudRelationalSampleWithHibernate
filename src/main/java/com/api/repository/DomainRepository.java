package com.api.repository;

import com.api.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    Domain findByName(String name);
}
