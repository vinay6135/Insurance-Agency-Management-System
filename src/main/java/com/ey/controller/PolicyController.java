package com.ey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.ey.dto.request.PolicyRequestDTO;
import com.ey.dto.request.UpdatePolicyRequestDTO;
import com.ey.dto.response.PolicyResponseDTO;
import com.ey.entity.Policy;
import com.ey.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/insurance")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/add/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyResponseDTO> add(@Valid
            @RequestBody PolicyRequestDTO policy,
            @PathVariable Long categoryId) {

        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(policyService.add(policy, categoryId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PolicyResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(policyService.get(id));
    }

    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PolicyResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(policyService.getAll());
    }

    @GetMapping("/getByCategory/{id}")
    public ResponseEntity<List<PolicyResponseDTO>> getByCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(policyService.getByCategory(id));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyResponseDTO> update(
            @PathVariable Long id,
            @RequestBody UpdatePolicyRequestDTO updatepolicy) {

        return ResponseEntity.ok(policyService.update(id, updatepolicy));
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PolicyResponseDTO> status(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request) {

        return ResponseEntity.ok(policyService.updateStatus(id, request.get("active")));
    }

//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        policyService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}

