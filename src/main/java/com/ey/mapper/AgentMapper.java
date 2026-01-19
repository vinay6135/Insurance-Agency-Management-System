package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.AgentRequestDTO;
import com.ey.dto.request.UpdateAgentRequestDTO;
import com.ey.dto.response.AgentResponseDTO;
import com.ey.entity.Agent;

@Component
public class AgentMapper {
	
	public Agent toEntity(AgentRequestDTO request)
	{
		Agent agent=new Agent();
		agent.setName(request.getName());
		agent.setPhone(request.getPhone());
		agent.setEmail(request.getEmail());
		return agent;
	}
	public AgentResponseDTO toResponse(Agent agent)
	{
		AgentResponseDTO response=new AgentResponseDTO();
		response.setId(agent.getId());
		response.setName(agent.getName());
		response.setPhone(agent.getPhone());
		response.setEmail(agent.getEmail());
		return response;
	}
	public Agent updateAgent(Agent agent,UpdateAgentRequestDTO update)
	{
		agent.setName(update.getName());
		agent.setPhone(update.getPhone());
		return agent;
	}

}
