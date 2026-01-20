package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.response.CommissionResponseDTO;
import com.ey.entity.AgentCommission;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.PremiumPayment;
import com.ey.enums.PaymentStatus;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.AgentCommissionRepository;
import com.ey.repository.AgentRepository;
import com.ey.repository.PremiumPaymentRepository;

@Service
public class CommissionService {
	org.slf4j.Logger logger  = LoggerFactory.getLogger(CommissionService.class);

    @Autowired
    private AgentCommissionRepository repository;

    public CommissionResponseDTO createCommission(AgentCommission ac) {

        CommissionResponseDTO resdto=new CommissionResponseDTO();
        resdto.setPremiumId(ac.getId());
        resdto.setAgentId(ac.getAgent().getId());
        resdto.setAmount(ac.getCustomerPolicy().getPolicy().getPremiumAmount());
        resdto.setCommision(ac.getAmount());
        return resdto;
    }

    public List<CommissionResponseDTO> getAgentCommissions(Long agentId) {
    	logger.info("Fetching commissions for AgentId={}", agentId);
    	List<AgentCommission> list=repository.findByAgentId(agentId);
    	if(!list.isEmpty())
    	{
    		List<CommissionResponseDTO> resdto=new ArrayList<>();
    		for(AgentCommission agc:list)
    		{
    			resdto.add(createCommission(agc));
    		}
    		return resdto;
    	}
    	logger.warn("No commissions found for AgentId={}", agentId);
    	throw new ResourceNotFoundException("No commissions Found");     
    }

    public List<CommissionResponseDTO> getAll() {
    	logger.info("Fetching all agent commissions");
    	List<AgentCommission> list=repository.findAll();
    	if(!list.isEmpty())	
    	{
    		List<CommissionResponseDTO> reslist=new ArrayList<>();
    		
    		for(AgentCommission agc:list)
    		{
    			reslist.add(createCommission(agc));
    				
    		}
    		return reslist;
    	}
    	logger.warn("No commissions found in system");
    	throw new ResourceNotFoundException("No commissions");
    	
        
    }
}

