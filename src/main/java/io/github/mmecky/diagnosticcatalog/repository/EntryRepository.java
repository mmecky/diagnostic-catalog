package io.github.mmecky.diagnosticcatalog.repository;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
