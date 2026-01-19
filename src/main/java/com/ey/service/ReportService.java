package com.ey.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.response.ReportResponseDTO;
import com.ey.entity.AgentCommission;
import com.ey.entity.Claim;
import com.ey.entity.CustomerPolicy;
import com.ey.enums.ClaimStatus;
import com.ey.enums.PolicyStatus;
import com.ey.repository.AgentCommissionRepository;
import com.ey.repository.ClaimRepository;
import com.ey.repository.CustomerPolicyRepository;

@Service
public class ReportService {

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    @Autowired
    private ClaimRepository claimRepository;

//    @Autowired
//    private AgentCommissionRepository commissionRepository;

    public ReportResponseDTO summary() {
    	
    	ReportResponseDTO resdto=new ReportResponseDTO();
    	List<CustomerPolicy> list=customerPolicyRepository.findByStatus(PolicyStatus.ACTIVE);
    	int totalclaims=claimRepository.countByStatusIn(List.of(ClaimStatus.SUBMITTED,ClaimStatus.VERIFIED));
    	int totalapproved=claimRepository.countByStatus(ClaimStatus.APPROVED);
    	
    	resdto.setTotalActivePolicies(list.size());
    	resdto.setTotalclaims(totalclaims);
    	resdto.setTotalapprovedclaims(totalapproved);
    	
    	return resdto;
    	

        
    }
}

