package com.ey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
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

import com.ey.dto.response.ClaimResponseDTO;
import com.ey.entity.Claim;
import com.ey.enums.ClaimStatus;
import com.ey.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ClaimResponseDTO> add(@RequestBody Map<String, String> request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.raiseClaim(
                        Long.valueOf(request.get("customerPolicyId")),
                        request.get("reason")
                ));
    }
    
    @GetMapping("/agent/claims")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<ClaimResponseDTO>> getAgentClaims(Authentication auth)
    {
    	return ResponseEntity.ok(service.getAssignedClaims(auth.getName()));
    }

    @PutMapping("/verify/{id}")
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public ResponseEntity<ClaimResponseDTO> update(
            @PathVariable Long id,Authentication auth
           ) {

        return ResponseEntity.ok(
                service.updateclaim(
                        id,auth.getName()
                        )
                
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Claim> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Claim>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
