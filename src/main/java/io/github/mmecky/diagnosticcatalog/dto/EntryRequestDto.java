package io.github.mmecky.diagnosticcatalog.dto;

import java.util.Set;

public record EntryRequestDto(String symptom, String cause, String remedy, Long componentId, Set<String> tags) {
}
