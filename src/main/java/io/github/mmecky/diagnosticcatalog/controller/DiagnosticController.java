package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import io.github.mmecky.diagnosticcatalog.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/entries")
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        Entry saved = entryRepository.save(entry);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }
}
