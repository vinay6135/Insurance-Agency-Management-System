package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.PolicyCategoryRequestDTO;
import com.ey.dto.request.UpdatePolicyCategoryRequest;
import com.ey.dto.response.PolicyCategoryResponseDTO;
import com.ey.entity.PolicyCategory;
import com.ey.service.PolicyCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy-category")
public class PolicyCategoryController {

    @Autowired
    private PolicyCategoryService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyCategoryResponseDTO> add(@Valid @RequestBody PolicyCategoryRequestDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(category));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PolicyCategoryResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PolicyCategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyCategoryResponseDTO> update(@Valid
            @PathVariable Long id,
            @RequestBody UpdatePolicyCategoryRequest category) {

        return ResponseEntity.ok(service.update(category));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
