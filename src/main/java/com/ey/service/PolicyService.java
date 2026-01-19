package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.PolicyRequestDTO;
import com.ey.dto.request.UpdatePolicyRequestDTO;
import com.ey.dto.response.PolicyResponseDTO;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.Policy;
import com.ey.entity.PolicyCategory;
import com.ey.enums.PolicyStatus;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.PolicySchemeMapper;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.PolicyCategoryRepository;
import com.ey.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	private PolicySchemeMapper policymapper;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyCategoryRepository categoryRepository;
    
    @Autowired
    private CustomerPolicyRepository customerpolicyRepository;

    public PolicyResponseDTO add(PolicyRequestDTO request, Long categoryId) {

        PolicyCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        
        Policy policy=policymapper.toEntity(request, category);
        Policy saved=policyRepository.save(policy);
        return policymapper.toResponse(saved);
    }

    public PolicyResponseDTO get(Long id) {
    	if(policyRepository.findById(id).isPresent())
    	{
    		return policymapper.toResponse(policyRepository.findById(id).get());
    	}
    	throw new ResourceNotFoundException("No Policy found With ID:"+id+".");
       
    }

    public List<PolicyResponseDTO> getAll() {
        List<Policy> list=policyRepository.findAll();
        if(!list.isEmpty())
        {
        	List<PolicyResponseDTO> dtolist=new ArrayList<>();
        	for(Policy policy:list)
        	{
        		dtolist.add(policymapper.toResponse(policy));
        	}
        	return dtolist;
        }
        throw new ResourceNotFoundException("Policy were not added yet");
    }

    public List<PolicyResponseDTO> getByCategory(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent())
        {
        	return policyRepository.findByCategoryIdAndActiveTrue(categoryId)
                    .stream()
                    .map(policymapper::toResponse)
                    .toList();
        }
        throw new ResourceNotFoundException("No Policy Category found with Id: "+categoryId);
    }

    public PolicyResponseDTO update(Long id, UpdatePolicyRequestDTO updated) {
       
    	Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with Id:"+id));
    	policymapper.updatepolicy(policy, updated);
    	return policymapper.toResponse(policyRepository.save(policy));
    }

    public PolicyResponseDTO updateStatus(Long id, boolean active) {
        Policy policy=policyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Policy not found with Id:"+id));
        policy.setActive(active);
        policyRepository.save(policy);
        return policymapper.toResponse(policy);
        		
    }

//    public void delete(Long id) {
//    	
//    	List<CustomerPolicy> list=customerpolicyRepository.findByStatus(PolicyStatus.ACTIVE);
//    	if(!list.isEmpty())
//    	{
//    		
//    	}
        
    
}
