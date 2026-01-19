package com.ey.controller;

import java.util.List;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.ey.entity.AgentCommission;
import com.ey.service.CommissionService;

@RestController
@RequestMapping("/commission")
public class CommissionController {

  @Autowired
  private CommissionService service;

  @GetMapping("/getAll")
  @PreAuthorize("hasRole('AGENT')")
  public ResponseEntity<List<AgentCommission>> agentCommissions(
          Authentication auth) {

      return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/admin/getAll")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<AgentCommission>> adminCommissions() {
      return ResponseEntity.ok(service.getAll());
  }
}
