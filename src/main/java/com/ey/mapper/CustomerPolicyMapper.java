package com.ey.mapper;

import java.net.Authenticator.RequestorType;

import org.springframework.stereotype.Component;

import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.CustomerPolicy;

@Component
public class CustomerPolicyMapper {
	
	public CustomerPolicyResponseDTO toResponse(CustomerPolicy request)
	{
		CustomerPolicyResponseDTO response=new CustomerPolicyResponseDTO();
		response.setCustomerPolicyId(request.getId());
		response.setPolicyId(request.getId());
		response.setPolicyName(request.getPolicy().getPolicyName());
		response.setCoverageAmount(request.getPolicy().getCoverageAmount());
		response.setPremiumAmount(request.getPolicy().getPremiumAmount());
		response.setStatus(request.getStatus());
		//response.setInstallmentType(null);
		return response;
	}

}
