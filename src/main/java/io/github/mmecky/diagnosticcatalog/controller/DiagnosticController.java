package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosticController {

    private static final List<Entry> ENTRIES = List.of();

    @GetMapping("/entries")
    public List<Entry> getEntries() {
        return ENTRIES;
    }
}
