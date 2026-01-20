package com.ey.controller;

import java.util.List;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.ey.dto.response.CommissionResponseDTO;
import com.ey.entity.AgentCommission;
import com.ey.service.CommissionService;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {

  @Autowired
  private CommissionService service;

  @GetMapping("/getAll/{id}")
  @PreAuthorize("hasRole('AGENT')")
  public ResponseEntity<List<CommissionResponseDTO>> getAgentCommissions(@PathVariable long id,
          Authentication auth) {

      return ResponseEntity.ok(service.getAgentCommissions(id));
  }

  @GetMapping("/admin/getAll")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<CommissionResponseDTO>> adminCommissions() {
      return ResponseEntity.ok(service.getAll());
  }
}
