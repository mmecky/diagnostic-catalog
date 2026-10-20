package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import io.github.mmecky.diagnosticcatalog.repository.EntryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosticController {

    private final EntryRepository entryRepository;

    public DiagnosticController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @GetMapping("/entries")
    public List<Entry> getEntries() {
        return entryRepository.findAll();
    }
}
