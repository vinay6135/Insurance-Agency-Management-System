package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.PolicyRequestDTO;
import com.ey.dto.request.UpdatePolicyRequestDTO;
import com.ey.dto.response.PolicyResponseDTO;
import com.ey.entity.Policy;
import com.ey.entity.PolicyCategory;

@Component
public class PolicySchemeMapper {
	
	public Policy toEntity(PolicyRequestDTO request,PolicyCategory category)
	{
		Policy policy=new Policy();
		policy.setCategory(category);
		policy.setPolicyName(request.getPolicyName());
		policy.setCoverageAmount(request.getCoverageAmount());
		policy.setPremiumAmount(request.getPremiumAmount());
		policy.setDurationYears(request.getDurationYears());
		policy.setActive(true);
		return policy;
		
	}
	
	public PolicyResponseDTO toResponse(Policy policy)
	{
		PolicyResponseDTO dto = new PolicyResponseDTO();
        dto.setId(policy.getId());
        dto.setPolicyName(policy.getPolicyName());
        dto.setCoverageAmount(policy.getCoverageAmount());
        dto.setPremiumAmount(policy.getPremiumAmount());
        dto.setDurationYears(policy.getDurationYears());
        dto.setActive(policy.isActive());
        dto.setCategoryid(policy.getCategory().getId());
        dto.setCategoryName(policy.getCategory().getCategoryName());
        dto.setDescription(policy.getCategory().getCategoryDescription());
        return dto;
	}
	
	public void updatepolicy(Policy policy, UpdatePolicyRequestDTO request)
	{
		policy.setCoverageAmount(request.getCoverageAmount());
		policy.setDurationYears(request.getDurationYears());
		policy.setPremiumAmount(request.getPremiumAmount());
		policy.setActive(request.getActive());
		
	}

}
