package com.ey.controller;

import java.util.List;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.ey.dto.response.NotificationResponseDTO;
import com.ey.entity.Notification;
import com.ey.repository.NotificationRepository;
import com.ey.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationservice;

  @Autowired
  private NotificationRepository repository;

  @GetMapping("/getAll/{id}")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<List<NotificationResponseDTO>> getAll(@PathVariable long id,Authentication auth) {
  	return ResponseEntity.ok(notificationservice.toResponse(id,auth.getName()));

      
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
      repository.deleteById(id);
      return ResponseEntity.noContent().build();
  }
}
