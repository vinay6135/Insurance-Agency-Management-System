package com.ey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.CustomerPolicy;
import com.ey.enums.InstallmentType;
import com.ey.service.PremiumService;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    @Autowired
    private PremiumService service;

    @GetMapping("/preview/{customerPolicyId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> preview(@PathVariable Long customerPolicyId, Authentication auth) {
        return ResponseEntity.ok(service.preview(customerPolicyId,auth.getName()));
    }

    @PostMapping("/pay")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CustomerPolicyResponseDTO> pay(@RequestBody Map<String, String> request,Authentication auth) {

        return ResponseEntity.ok(
                service.pay(
                        Long.valueOf(request.get("customerPolicyId")),
                        InstallmentType.valueOf(request.get("installmentType")),auth.getName()
                )
        );
    }
}
