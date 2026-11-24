package io.github.mmecky.diagnosticcatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record EntryRequestDto(@NotBlank @Size(max = 200) String symptom, @NotBlank @Size(max = 1000) String cause,
                              @NotBlank @Size(max = 2000) String remedy, @NotNull Long componentId,
                              @NotNull Set<@NotBlank @Size(max = 50) String> tags) {
}
