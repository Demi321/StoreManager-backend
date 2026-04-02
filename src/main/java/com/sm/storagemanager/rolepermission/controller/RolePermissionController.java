package com.sm.storagemanager.rolepermission.controller;

import com.sm.storagemanager.rolepermission.dto.RolePermissionDto;
import com.sm.storagemanager.rolepermission.entity.RolePermissionId;
import com.sm.storagemanager.rolepermission.service.RolePermissionService;
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
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @GetMapping
    public List<RolePermissionDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{roleId}/{permissionId}")
    public ResponseEntity<RolePermissionDto> findById(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return service.findById(new RolePermissionId(roleId, permissionId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolePermissionDto> create(@RequestBody RolePermissionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{roleId}/{permissionId}")
    public ResponseEntity<RolePermissionDto> update(
            @PathVariable Long roleId,
            @PathVariable Long permissionId,
            @RequestBody RolePermissionDto dto
    ) {
        RolePermissionId id = new RolePermissionId(roleId, permissionId);
        return ResponseEntity.ok(service.update(id, withIds(roleId, permissionId, dto)));
    }

    @DeleteMapping("/{roleId}/{permissionId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long roleId, @PathVariable Long permissionId) {
        service.deleteById(new RolePermissionId(roleId, permissionId));
        return ResponseEntity.noContent().build();
    }

    private RolePermissionDto withIds(Long roleId, Long permissionId, RolePermissionDto dto) {
        return new RolePermissionDto(roleId, permissionId, dto.createdAt());
    }
}
