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

import com.ey.dto.request.AgentRequestDTO;
import com.ey.dto.response.AgentResponseDTO;
import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.Agent;
import com.ey.entity.CustomerPolicy;
import com.ey.service.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgentResponseDTO> addAgent(@Valid @RequestBody AgentRequestDTO agent) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agentService.addAgent(agent));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgentResponseDTO> getAgent(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(agentService.getAgent(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AgentResponseDTO>> getAll() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }
    @PutMapping("/assign/{customerPolicyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerPolicyResponseDTO> assign(
            @PathVariable Long customerPolicyId,
            @RequestBody Map<String, Long> request) {

        return ResponseEntity.ok(
                agentService.assignAgent(customerPolicyId, request.get("agentId"))
        );
    }

}
