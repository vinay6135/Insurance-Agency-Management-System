package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.entity.Claim;
import com.ey.enums.ClaimStatus;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
	List<Claim> findByCustomerPolicyAgentIdAndStatus(
		    Long agentId,
		    ClaimStatus status
		);

}

