package com.ey.service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private AgentCommissionRepository repository;
    
    @Autowired
    private PremiumPaymentRepository premiumrepo;

    public CommissionResponseDTO createCommission(PremiumPayment pp) {

        AgentCommission commission = new AgentCommission();
        commission.setAgent(pp.getCustomerPolicy().getAgent());
        commission.setCustomerPolicy(pp.getCustomerPolicy());

        commission.setAmount(pp.getAmount()* 0.10);
        AgentCommission cr=repository.save(commission);

        CommissionResponseDTO resdto=new CommissionResponseDTO();
        resdto.setPremiumId(pp.getId());
        resdto.setAgentId(cr.getAgent().getId());
        resdto.setAmount(pp.getAmount());
        resdto.setCommision(cr.getAmount());
        return resdto;
    }

    public List<CommissionResponseDTO> getAgentCommissions(Long agentId) {
    	List<PremiumPayment> list=premiumrepo.findByCustomerPolicyAgentIdAndStatus(agentId,PaymentStatus.PAID);
    	if(!list.isEmpty())	
    	{
    		List<CommissionResponseDTO> reslist=new ArrayList<>();
    		
    		for(PremiumPayment payment:list)
    		{
    			reslist.add(createCommission(payment));
    				
    		}
    		return reslist;
    	}
    	throw new ResourceNotFoundException("No commissions added");
        
    }

    public List<CommissionResponseDTO> getAll() {
    	List<PremiumPayment> list=premiumrepo.findByStatus(PaymentStatus.PAID);
    	if(!list.isEmpty())	
    	{
    		List<CommissionResponseDTO> reslist=new ArrayList<>();
    		
    		for(PremiumPayment payment:list)
    		{
    			reslist.add(createCommission(payment));
    				
    		}
    		return reslist;
    	}
    	throw new ResourceNotFoundException("No commissions");
    	
        
    }
}

