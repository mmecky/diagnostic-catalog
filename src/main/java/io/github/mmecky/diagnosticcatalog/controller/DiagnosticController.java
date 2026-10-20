package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import io.github.mmecky.diagnosticcatalog.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/entries/{id}")
    public Entry getEntryById(@PathVariable Long id) {
        return entryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
