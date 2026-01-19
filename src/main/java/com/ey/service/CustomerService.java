package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CustomerRequestDTO;
import com.ey.dto.request.UpdateCustomerRequest;
import com.ey.dto.response.CustomerResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.User;
import com.ey.exception.AccessDeniedException;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.CustomerMapper;
import com.ey.repository.CustomerRepository;
import com.ey.repository.UserRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerMapper customermapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerdto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (customerRepository.existsByUser(user)) {
            throw new BusinessException("Customer profile already exists",HttpStatus.CONFLICT);
        }
        
        Customer customer=customermapper.toEntity(customerdto, user);
        Customer saved=customerRepository.save(customer);
        return customermapper.toResponse(saved);
             
    }

    public CustomerResponseDTO getCustomer(Long id,String email) {
    	
    	Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        if (!customer.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException("You are not authorized to access this customer");
        }

        return customermapper.toResponse(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> list=customerRepository.findAll();
        if(!list.isEmpty())
        {
        	List<CustomerResponseDTO> dtolist=new ArrayList<>();
        	for(Customer customer:list)
        	{
        		dtolist.add(customermapper.toResponse(customer));
        	}
        	return dtolist;    	
        }
        throw new ResourceNotFoundException("No Customer created account");
    }

    public CustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequest updated,String loggedInEmail) {
    	User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    	
    	Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    	
    	if (!customer.getUser().getId().equals(user.getId())) {
    		throw new BusinessException("You are not allowed to upadte other details",HttpStatus.FORBIDDEN);
            
        }
    	
    	Customer updatedcustomer=customermapper.updateAllowedFields(customer, updated);
    	Customer saved=customerRepository.save(updatedcustomer);
    	return customermapper.toResponse(saved);
    	


    }

    public void deleteCustomer(Long id) {
        if(customerRepository.findById(id).isEmpty())
        {
        	throw new ResourceNotFoundException("No customer with id:"+id+" exist");
        }
        customerRepository.deleteById(id);
    }
}

