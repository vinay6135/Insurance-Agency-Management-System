package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.Policy;
import com.ey.enums.PolicyStatus;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.CustomerPolicyMapper;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.CustomerRepository;
import com.ey.repository.PolicyRepository;

@Service
public class CustomerPolicyService {
	@Autowired
	private CustomerPolicyMapper customerpolicymapper;

    @Autowired
    private CustomerPolicyRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public CustomerPolicyResponseDTO purchasePolicy(Long policyId, String email) {

        Customer customer = customerRepository.findAll().stream()
                .filter(c -> c.getUser().getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer profile not found"));

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        if (!policy.isActive()) {
            throw new ResourceNotFoundException("Policy is inactive");
        }
      Optional<CustomerPolicy> existCustomer= repository.findByCustomerAndPolicyAndStatusIn(
                customer,
                policy,
                List.of(
                    PolicyStatus.PENDING_PAYMENT,
                    PolicyStatus.ACTIVE
                )
        );
      if(existCustomer.isPresent())
      {
    	  if(existCustomer.get().getStatus().equals(PolicyStatus.PENDING_PAYMENT))
    	  {
    		  throw new BusinessException("You have already Purchased this Policy please complete the payment",HttpStatus.CONFLICT);		  
    	  }
    	  if(existCustomer.get().getStatus().equals(PolicyStatus.ACTIVE))
    	  {
    		  throw new BusinessException("You are an Active Customer of this policy you cannot buy again this Policy with Id:"+policyId,HttpStatus.CONFLICT);
    	  }
    	  
      }
        CustomerPolicy cp = new CustomerPolicy();
        cp.setCustomer(customer);
        cp.setPolicy(policy);
        cp.setStatus(PolicyStatus.PENDING_PAYMENT);

        CustomerPolicy saved = repository.save(cp);
        return customerpolicymapper.toResponse(saved);
    }


    public CustomerPolicyResponseDTO get(Long id) {
    	if(repository.findById(id).isPresent())
    	{
    		return customerpolicymapper.toResponse(repository.findById(id).get());
    	}
    	throw new ResourceNotFoundException("Customer Policy not found with Id:"+id);
       
    }

    public List<CustomerPolicyResponseDTO> getAll() {
        List<CustomerPolicy> list=repository.findAll();
        if(!list.isEmpty())
        {
        	List<CustomerPolicyResponseDTO> dtolist=new ArrayList<>();
        	for(CustomerPolicy cpolicy:list)
        	{
        		dtolist.add(customerpolicymapper.toResponse(cpolicy));
        	}
        	return dtolist;
        }
        throw new ResourceNotFoundException("NO customer Policies created");
    }

    public String cancel(Long id,String email) {
        if(repository.findById(id).isPresent())
        {
        	if(repository.findById(id).get().getCustomer().getUser().getEmail().equals(email))
        	{
        		CustomerPolicy cp=repository.findById(id).get();
        		cp.setStatus(PolicyStatus.CANCELLED);
        		repository.save(cp);
        		return "Customer Policy successfully canceled";
        	}
        }
        throw new ResourceNotFoundException("CustomerPolicy Not Found with Id:"+id);
    }
}

