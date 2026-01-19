package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.Agent;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.User;

public interface AgentRepository extends JpaRepository<Agent, Long> {

	boolean existsByUser(User user);

	Optional<Agent> findByUserEmail(String agentEmail);
	
}

