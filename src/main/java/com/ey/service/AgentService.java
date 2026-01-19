package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AgentRequestDTO;
import com.ey.dto.response.AgentResponseDTO;
import com.ey.dto.response.CustomerPolicyResponseDTO;
import com.ey.entity.Agent;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.User;
import com.ey.enums.Role;
import com.ey.exception.BusinessException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.mapper.AgentMapper;
import com.ey.mapper.CustomerPolicyMapper;
import com.ey.repository.AgentRepository;
import com.ey.repository.CustomerPolicyRepository;
import com.ey.repository.UserRepository;

@Service
public class AgentService {
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

    public AgentResponseDTO addAgent(AgentRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BusinessException("Agent must signup first using same email",HttpStatus.NOT_FOUND));

        if (user.getRole() != Role.AGENT) {
            throw new BusinessException("User is not an AGENT",HttpStatus.BAD_REQUEST);
        }

        if (agentRepository.existsByUser(user)) {
            throw new BusinessException("Agent profile already exists",HttpStatus.CONFLICT);
        }

        Agent agent=agentmapper.toEntity(request);
        agent.setUser(user);
        Agent saved=agentRepository.save(agent);
        return agentmapper.toResponse(saved);
    }

    
    public CustomerPolicyResponseDTO assignAgent(Long customerPolicyId, Long agentId) {

        CustomerPolicy cp = customerPolicyRepository.findById(customerPolicyId)
                .orElseThrow(() -> new BusinessException("Customer policy not found",HttpStatus.NOT_FOUND));

        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new BusinessException("Agent not found",HttpStatus.NOT_FOUND));

        cp.setAgent(agent);
        CustomerPolicy saved=customerPolicyRepository.save(cp);
        CustomerPolicyResponseDTO resdto=cpmapper.toResponse(saved);
        resdto.setAgentPhone(agent.getPhone());
        return resdto;
    }

    public AgentResponseDTO getAgent(Long id) {
       if(agentRepository.findById(id).isPresent())
       {
    	   return agentmapper.toResponse(agentRepository.findById(id).get());
       }
       throw new ResourceNotFoundException("No Agent Found With Id: "+id);
    }

    public List<AgentResponseDTO> getAllAgents() {
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

