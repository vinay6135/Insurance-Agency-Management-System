package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AgentRequestDTO;
import com.ey.dto.response.AgentResponseDTO;
import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.Agent;
import com.ey.entity.AgentCommission;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.PremiumPayment;
import com.ey.entity.User;
import com.ey.enums.InstallmentType;
import com.ey.enums.PolicyStatus;
import com.ey.enums.Role;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.AgentMapper;
import com.ey.mapper.CustomerPolicyMapper;
import com.ey.repository.AgentCommissionRepository;
import com.ey.repository.AgentRepository;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.PremiumPaymentRepository;
import com.ey.repository.UserRepository;

@Service
public class AgentService {
	org.slf4j.Logger logger  = LoggerFactory.getLogger(AgentService.class);
	
	@Autowired
	private AgentMapper agentmapper;
	
	@Autowired
	private CustomerPolicyMapper cpmapper;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;
    
    @Autowired
    private AgentCommissionRepository acrepo;
    
    @Autowired
    private PremiumPaymentRepository pprepo;

    public AgentResponseDTO addAgent(AgentRequestDTO request) {
    	logger.info("Attempting to add agent with email: {}", request.getEmail());

    	User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Agent did not signup or failed . No user found with email: {}", request.getEmail());
                    return new BusinessException(
                            "Agent must signup first using same email",
                            HttpStatus.NOT_FOUND
                    );
                });

        if (user.getRole() != Role.AGENT) {
            throw new BusinessException("User is not an AGENT",HttpStatus.BAD_REQUEST);
        }

        if (agentRepository.existsByUser(user)) {
        	logger.warn("Agent profile already exists for email: {}", request.getEmail());
            throw new BusinessException("Agent profile already exists",HttpStatus.CONFLICT);
        }

        Agent agent=agentmapper.toEntity(request);
        agent.setUser(user);
        Agent saved=agentRepository.save(agent);
        logger.info("Agent profile created successfully. AgentId={}, Email={}",
                saved.getId(), saved.getEmail());
        return agentmapper.toResponse(saved);
    }

    
    public CustomerPolicyResponseDTO assignAgent(Long customerPolicyId, Long agentId) {
    	logger.info("Assigning AgentId={} to CustomerPolicyId={}", agentId, customerPolicyId);

    	CustomerPolicy cp = customerPolicyRepository.findById(customerPolicyId)
                .orElseThrow(() -> {
                    logger.warn("CustomerPolicy not found for id={}", customerPolicyId);
                    return new ResourceNotFoundException("Customer policy not found");
                });

        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found"));

        cp.setAgent(agent);
        CustomerPolicy saved=customerPolicyRepository.save(cp);
        
        logger.info("AgentId={} successfully assigned to CustomerPolicyId={}",
                agentId, customerPolicyId);
        
        CustomerPolicyResponseDTO resdto=cpmapper.toResponse(saved);
        resdto.setAgentPhone(agent.getPhone());
        	List<AgentCommission> list=acrepo.findByCustomerPolicyId(customerPolicyId);
        	if(!list.isEmpty())
        	{
        		for(AgentCommission ac:list)
        		{
        			ac.setAgent(agent);
        			acrepo.save(ac);
        		}
        	}  	
        
        
        
        List<PremiumPayment> pp=pprepo.findByCustomerPolicyId(customerPolicyId);
        
        resdto.setInstallmentType(pp.get(0).getInstallmentType());
        return resdto;
    }

    public AgentResponseDTO getAgent(Long id) {
    	
       logger.info("Fetching Agent with id={}", id);
       if(agentRepository.findById(id).isPresent())
       {
    	   return agentmapper.toResponse(agentRepository.findById(id).get());
       }
       throw new ResourceNotFoundException("No Agent Found With Id: "+id);
    }

    public List<AgentResponseDTO> getAllAgents() {
    	
    	 logger.info("Fetching all agents");
        List<Agent> list=agentRepository.findAll();
        if(!list.isEmpty())
        {
        	List<AgentResponseDTO> dtolist=new ArrayList<>();
        	for(Agent agent:list)
        	{
        		dtolist.add(agentmapper.toResponse(agent));
        	}
        	return dtolist;
        }
        throw new BusinessException("No Agent Found",HttpStatus.NOT_FOUND);
    }
}

