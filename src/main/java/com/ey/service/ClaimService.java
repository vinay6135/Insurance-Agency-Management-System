package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ey.dto.response.ClaimResponseDTO;
import com.ey.entity.Agent;
import com.ey.entity.Claim;
import com.ey.entity.CustomerPolicy;
import com.ey.enums.ClaimStatus;
import com.ey.enums.PaymentStatus;
import com.ey.enums.PolicyStatus;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.ClaimMapper;
import com.ey.repository.AgentRepository;
import com.ey.repository.ClaimRepository;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.PremiumPaymentRepository;

@Service
public class ClaimService {
	
	@Autowired
	private ClaimMapper claimmapper;
	
	@Autowired
	private AgentRepository agentRepository;

    @Autowired
    private ClaimRepository repository;

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private PremiumPaymentRepository pprepo;

    public ClaimResponseDTO raiseClaim(Long customerPolicyId, String reason) {

        CustomerPolicy cp = customerPolicyRepository.findById(customerPolicyId)
                .orElseThrow(() -> new RuntimeException("Customer policy not found"));

        Claim claim = new Claim();
        claim.setCustomerPolicy(cp);
        claim.setReason(reason);
        claim.setStatus(ClaimStatus.SUBMITTED);

        notificationService.notify(
                cp.getCustomer(),
                "Claim submitted successfully"
        );

        repository.save(claim);
        return claimmapper.toResponse(claim);
    }
    
    public List<ClaimResponseDTO> getAssignedClaims(String agentEmail) {

        Agent agent = agentRepository.findByUserEmail(agentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));
        
        List<Claim> list=repository.findByCustomerPolicyAgentIdAndStatus(agent.getId(),ClaimStatus.SUBMITTED);
        if(!list.isEmpty())
        {
        	List<ClaimResponseDTO> resdto=new ArrayList<>();
        	for(Claim claim:list)
        	{
        		resdto.add(claimmapper.toResponse(claim));
        	}
        	return resdto;	
        }
        throw new ResourceNotFoundException("No Claims Assigned");
    }

    public ClaimResponseDTO updateclaim(Long claimid, String agentEmail) {
    	
    	Agent agent = agentRepository.findByUserEmail(agentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));

        Claim claim = repository.findById(claimid)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));
        
        CustomerPolicy policy = claim.getCustomerPolicy();
        
        if (policy.getStatus() != PolicyStatus.ACTIVE) {
            throw new BusinessException("Policy is not active",HttpStatus.BAD_REQUEST);
        }
        
        boolean pendingPremium =
                pprepo.existsByCustomerPolicyIdAndStatus(
                        policy.getId(),
                        PaymentStatus.DUE
                );
        if (pendingPremium) {
            throw new BusinessException("Premium payment pending. Claim cannot be verified",HttpStatus.BAD_REQUEST);
        }
        claim.setStatus(ClaimStatus.VERIFIED);

        notificationService.notify(
                claim.getCustomerPolicy().getCustomer(),
                "Your claim has been VERIFIED by Agent "
                        + agent.getName()
                        + ". It is now pending admin approval."
        );

        repository.save(claim);
        return claimmapper.toResponse(claim);
    }

    public Claim get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    public List<Claim> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

