package io.github.mmecky.diagnosticcatalog.repository;

import io.github.mmecky.diagnosticcatalog.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {
}
