package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.dto.EntryRequestDto;
import io.github.mmecky.diagnosticcatalog.dto.EntryResponseDto;
import io.github.mmecky.diagnosticcatalog.service.EntryService;
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

    private final EntryService entryService;

    public DiagnosticController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/entries")
    public List<EntryResponseDto> getEntries() {
        return entryService.findAll();
    }

    @GetMapping("/entries/{id}")
    public EntryResponseDto getEntryById(@PathVariable Long id) {
        return entryService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/entries")
    public ResponseEntity<EntryResponseDto> createEntry(@RequestBody EntryRequestDto request) {
        EntryResponseDto response = entryService.save(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }
}
