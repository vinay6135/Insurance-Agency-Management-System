package com.ey.controller;

import java.util.List;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.security.core.Authentication;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.request.UpdateCustomerRequest;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.entity.Customer;
import com.ey.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<CustomerResponseDTO> addCustomer(@Valid
          @RequestBody CustomerRequestDTO customerdto,
          Authentication authentication) {

      if (authentication == null || !authentication.isAuthenticated()) {
          throw new RuntimeException("User not authenticated");
      }

      return ResponseEntity.status(HttpStatus.CREATED)
              .body(customerService.addCustomer(customerdto, authentication.getName()));
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<CustomerResponseDTO> getCustomer(@Valid @PathVariable Long id, Authentication authentication) {
  	String email=authentication.getName();
      return ResponseEntity.ok(customerService.getCustomer(id, email));
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<CustomerResponseDTO>> getAll() {
  	
  	List<CustomerResponseDTO> response =
              customerService.getAllCustomers();
      return ResponseEntity.ok(response);
  }

  @PutMapping("/edit/{id}")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<CustomerResponseDTO> update(
          @PathVariable Long id,@Valid
          @RequestBody UpdateCustomerRequest updatedto,Authentication authentication) {
  	CustomerResponseDTO response=customerService.updateCustomer(id, updatedto, authentication.getName());
  	return ResponseEntity.ok(response); 

      
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> deleteCustomer(@Valid @PathVariable Long id) {

      customerService.deleteCustomer(id);

      return ResponseEntity.ok("Customer deleted successfully");
  }
}
