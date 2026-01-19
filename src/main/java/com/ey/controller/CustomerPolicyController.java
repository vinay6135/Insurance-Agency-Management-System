package com.ey.controller;

import java.util.List;
import java.util.Map;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.CustomerPolicy;
import com.ey.service.CustomerPolicyService;

@RestController
@RequestMapping("/customer-policy")
public class CustomerPolicyController {

    @Autowired
    private CustomerPolicyService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CustomerPolicyResponseDTO> purchase(
            @RequestBody Map<String, Long> request,
            Authentication auth) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.purchasePolicy(request.get("policyId"), auth.getName()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerPolicyResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerPolicyResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> cancel(@PathVariable Long id,Authentication auth) {
        return ResponseEntity.ok(service.cancel(id,auth.getName()));
    }
}
