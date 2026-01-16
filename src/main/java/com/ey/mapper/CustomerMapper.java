package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.User;
@Component
public class CustomerMapper {
	 public Customer toEntity(CustomerRequestDTO dto, User user) {

	        Customer c = new Customer();
	        c.setUser(user);
	        c.setFullName(dto.getFullName());
	        c.setDateofBirth(dto.getDateofBirth());
	        c.setAge(dto.getAge());
	        c.setGender(dto.getGender());
	        c.setContactNumber(dto.getContactNumber());
	        c.setAddress(dto.getAddress());
	        c.setNomineeName(dto.getNomineeName());
	        c.setNomineeRelation(dto.getNomineeRelation());
	        c.setNomineephone(dto.getNomineePhone());
	        c.setIdentityProofNumber(dto.getIdentityProofNumber());

	        return c;
	    }
	 public CustomerResponseDTO toResponse(Customer c) {

	        CustomerResponseDTO dto = new CustomerResponseDTO();
	        dto.setId(c.getId());
	        dto.setFullName(c.getFullName());
	        dto.setDateOfBirth(c.getDateofBirth());
	        dto.setAge(c.getAge());
	        dto.setGender(c.getGender());
	        dto.setContactNumber(c.getContactNumber());
	        dto.setAddress(c.getAddress());
	        dto.setNomineeName(c.getNomineeName());
	        dto.setNomineeRelation(c.getNomineeRelation());
	        dto.setNomineephone(c.getNomineephone());
	        dto.setIdentityProofNumber(c.getIdentityProofNumber());
	        dto.setCreatedAt(c.getCreatedAt());
	        dto.setUpdatedAt(c.getUpdatedAt());
	        return dto;
	    }

}
