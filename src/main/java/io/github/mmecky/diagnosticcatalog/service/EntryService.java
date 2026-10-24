package io.github.mmecky.diagnosticcatalog.service;

import io.github.mmecky.diagnosticcatalog.dto.ComponentResponseDto;
import io.github.mmecky.diagnosticcatalog.dto.EntryRequestDto;
import io.github.mmecky.diagnosticcatalog.dto.EntryResponseDto;
import io.github.mmecky.diagnosticcatalog.model.Component;
import io.github.mmecky.diagnosticcatalog.model.Entry;
import io.github.mmecky.diagnosticcatalog.repository.ComponentRepository;
import io.github.mmecky.diagnosticcatalog.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EntryService {

    private final EntryRepository entryRepository;
    private final ComponentRepository componentRepository;

    public EntryService(EntryRepository entryRepository, ComponentRepository componentRepository) {
        this.entryRepository = entryRepository;
        this.componentRepository = componentRepository;
    }

    @Transactional(readOnly = true)
    public List<EntryResponseDto> findAll() {
        List<Entry> entries = entryRepository.findAll();
        List<EntryResponseDto> dtos = entries.stream().map(this::toResponseDto).toList();
        return dtos;
    }

    @Transactional(readOnly = true)
    public Optional<EntryResponseDto> findById(Long id) {
        Optional<Entry> oEntry = entryRepository.findById(id);
        if (!oEntry.isPresent()) {
            return Optional.empty();
        }
        Entry entry = oEntry.get();
        EntryResponseDto entryResponseDto = toResponseDto(entry);
        return Optional.of(entryResponseDto);
    }

    public EntryResponseDto save(EntryRequestDto entryRequestDto) {
        Component component = componentRepository.findById(entryRequestDto.componentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "component not found"));
        Entry entry = new Entry(
                entryRequestDto.symptom(),
                entryRequestDto.cause(),
                entryRequestDto.remedy(),
                component,
                entryRequestDto.tags()
        );
        Entry saved = entryRepository.save(entry);
        EntryResponseDto entryResponseDto = toResponseDto(saved);
        return entryResponseDto;
    }

    private EntryResponseDto toResponseDto(Entry entry) {
        ComponentResponseDto componentResponseDto = new ComponentResponseDto(entry.getComponent().getId(), entry.getComponent().getName());
        Set<String> loadedTags = new HashSet<>(entry.getTags()); // entry.tags is LAZY, so force it to load by copying it
        EntryResponseDto entryResponseDto = new EntryResponseDto(entry.getId(), entry.getSymptom(), entry.getCause(), entry.getRemedy(), componentResponseDto, loadedTags);
        return entryResponseDto;
    }
}
