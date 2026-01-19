package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.PolicyCategoryRequestDTO;
import com.ey.dto.request.UpdatePolicyCategoryRequest;
import com.ey.dto.response.PolicyCategoryResponseDTO;
import com.ey.entity.PolicyCategory;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.PolicyCategoryMapper;
import com.ey.repository.PolicyCategoryRepository;

@Service
public class PolicyCategoryService {
	@Autowired
	private PolicyCategoryMapper policymapper;

    @Autowired
    private PolicyCategoryRepository repository;

    public PolicyCategoryResponseDTO add(PolicyCategoryRequestDTO category) {
    	PolicyCategory policy=policymapper.toEntity(category);
    	PolicyCategory saved=repository.save(policy);
    	return policymapper.toResponse(saved);
    	
        
    }

    public PolicyCategoryResponseDTO get(Long id) {
        if(repository.findById(id).isPresent())
        {
        	return policymapper.toResponse(repository.findById(id).get());
        }
        throw new ResourceNotFoundException("Policy with id: "+id+" does not exist");
    }

    public List<PolicyCategoryResponseDTO> getAll() {
        List<PolicyCategory> list=repository.findAll();
        if(!list.isEmpty())
        {
        	List<PolicyCategoryResponseDTO> policyCRlist=new ArrayList<>();
        	for(PolicyCategory category:list)
        	{
        		policyCRlist.add(policymapper.toResponse(category));
        		
        	}
        	return policyCRlist;
        }
        throw new ResourceNotFoundException("No Policies are available");
    }

    public PolicyCategoryResponseDTO update(UpdatePolicyCategoryRequest updated) {
    	if(repository.findById(updated.getId()).isPresent())
    	{
        PolicyCategory category = repository.findById(updated.getId()).get();
        category.setCategoryDescription(updated.getCategoryDescription());
        PolicyCategory saved=repository.save(category);
        return policymapper.toResponse(saved);
    	}
    	else
    	{
    		throw new ResourceNotFoundException("No PolicyCategory Found with Id: "+updated.getId());
    	}
    }

    public void delete(Long id) {
        if(repository.findById(id).isEmpty())
        {
        	throw new ResourceNotFoundException("No Policy exist with Id:"+id+"to Delete");
        }
        repository.deleteById(id);
    }
}

