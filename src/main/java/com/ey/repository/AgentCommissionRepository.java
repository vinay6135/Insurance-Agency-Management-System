package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.AgentCommission;

public interface AgentCommissionRepository
extends JpaRepository<AgentCommission, Long> {

List<AgentCommission> findByAgentId(Long agentId);
}

