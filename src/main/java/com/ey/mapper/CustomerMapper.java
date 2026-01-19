package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.request.UpdateCustomerRequest;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.User;
@Component
public class CustomerMapper {
	 public Customer toEntity(CustomerRequestDTO dto, User user) {

	        Customer c = new Customer();
	        c.setUser(user);
	        c.setFullName(dto.getFullName());
	        c.setDateOfBirth(dto.getDateOfBirth());
	        c.setAge(dto.getAge());
	        c.setGender(dto.getGender());
	        c.setContactNumber(dto.getContactNumber());
	        c.setEmail(dto.getEmail());
	        c.setAddress(dto.getAddress());
	        c.setNomineeName(dto.getNomineeName());
	        c.setNomineeRelation(dto.getNomineeRelation());
	        c.setNomineePhone(dto.getNomineePhone());
	        c.setIdentityProofNumber(dto.getIdentityProofNumber());

	        return c;
	    }
	 public CustomerResponseDTO toResponse(Customer c) {

	        CustomerResponseDTO dto = new CustomerResponseDTO();
	        dto.setId(c.getId());
	        dto.setFullName(c.getFullName());
	        dto.setDateOfBirth(c.getDateOfBirth());
	        dto.setAge(c.getAge());
	        dto.setGender(c.getGender());
	        dto.setContactNumber(c.getContactNumber());
	        dto.setEmail(c.getEmail());
	        dto.setAddress(c.getAddress());
	        dto.setNomineeName(c.getNomineeName());
	        dto.setNomineeRelation(c.getNomineeRelation());
	        dto.setNomineePhone(c.getNomineePhone());
	        dto.setIdentityProofNumber(c.getIdentityProofNumber());
	        dto.setCreatedAt(c.getCreatedAt());
	        dto.setUpdatedAt(c.getUpdatedAt());
	        return dto;
	    }
	 
	 public Customer updateAllowedFields(
	            Customer customer,
	            UpdateCustomerRequest dto
	    ) {
	        customer.setContactNumber(dto.getContactNumber());
	        customer.setAddress(dto.getAddress());
	        customer.setNomineeName(dto.getNomineeName());
	        customer.setNomineeRelation(dto.getNomineeRelation());
	        customer.setNomineePhone(dto.getNomineePhone());
	        return customer;
	    }

}
