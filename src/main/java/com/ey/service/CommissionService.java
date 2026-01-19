package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.AgentCommission;
import com.ey.entity.CustomerPolicy;
import com.ey.repository.AgentCommissionRepository;

@Service
public class CommissionService {

    @Autowired
    private AgentCommissionRepository repository;

    public AgentCommission createCommission(CustomerPolicy cp) {

        AgentCommission commission = new AgentCommission();
        commission.setAgent(cp.getAgent());
        commission.setCustomerPolicy(cp);

        // example: 10% commission
        commission.setAmount(cp.getPolicy().getPremiumAmount() * 0.10);

        return repository.save(commission);
    }

    public List<AgentCommission> getAgentCommissions(Long agentId) {
        return repository.findByAgentId(agentId);
    }

    public List<AgentCommission> getAll() {
        return repository.findAll();
    }
}

