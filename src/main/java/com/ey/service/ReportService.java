package com.ey.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.AgentCommission;
import com.ey.repository.AgentCommissionRepository;
import com.ey.repository.ClaimRepository;
import com.ey.repository.CustomerPolicyRepository;

@Service
public class ReportService {

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private AgentCommissionRepository commissionRepository;

    public Map<String, Object> summary() {

        return Map.of(
                "totalPolicies", customerPolicyRepository.count(),
                "totalClaims", claimRepository.count(),
                "totalCommission", commissionRepository.findAll()
                        .stream()
                        .mapToDouble(AgentCommission::getAmount)
                        .sum()
        );
    }
}

