package io.github.mmecky.diagnosticcatalog.dto;

import java.util.Set;

public record EntryResponseDto(Long id, String symptom, String cause, String remedy,
                               ComponentResponseDto component, Set<String> tags) {
}
