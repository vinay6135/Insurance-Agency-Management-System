package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.ClaimRequestDTO;
import com.ey.dto.response.ClaimResponseDTO;
import com.ey.entity.Claim;

@Component
public class ClaimMapper {
	
	public Claim toEntity(ClaimRequestDTO request)
	{
		Claim claim=new Claim();
		claim.setReason(request.getReason());
		return claim;
	}
	public ClaimResponseDTO toResponse(Claim claim)
	{
		ClaimResponseDTO resdto=new ClaimResponseDTO();
		resdto.setClaimId(claim.getId());
		resdto.setCustomerId(claim.getCustomerPolicy().getCustomer().getId());
		resdto.setCustomerpolicyId(claim.getCustomerPolicy().getId());
		resdto.setPolicyName(claim.getCustomerPolicy().getPolicy().getPolicyName());
		resdto.setStatus(claim.getStatus());
		resdto.setCreatedAt(claim.getCreatedAt());
		return resdto;
	}

}
