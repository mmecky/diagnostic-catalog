package io.github.mmecky.diagnosticcatalog.model;

import java.util.Set;

public record Entry(
        Long id,
        String symptom,
        String cause,
        String remedy,
        String component,
        Set<String> tags
) {
}
