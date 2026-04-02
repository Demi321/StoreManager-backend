package com.sm.storagemanager.businessentity.controller;

import com.sm.storagemanager.businessentity.dto.BusinessEntityDto;
import com.sm.storagemanager.businessentity.service.BusinessEntityService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business-entities")
public class BusinessEntityController {

    private final BusinessEntityService service;

    public BusinessEntityController(BusinessEntityService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusinessEntityDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BusinessEntityDto findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<BusinessEntityDto> create(@RequestBody BusinessEntityDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessEntityDto> update(@PathVariable Long id, @RequestBody BusinessEntityDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
