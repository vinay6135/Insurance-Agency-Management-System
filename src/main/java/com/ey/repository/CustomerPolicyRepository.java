package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Customer;
import com.ey.entity.CustomerPolicy;
import com.ey.entity.Policy;
import com.ey.enums.PolicyStatus;

@Repository
public interface CustomerPolicyRepository
extends JpaRepository<CustomerPolicy, Long> {
	List<CustomerPolicy> findByStatus(PolicyStatus status);
	Optional<CustomerPolicy> findByCustomerAndPolicyAndStatusIn(
	        Customer customer,
	        Policy policy,
	        List<PolicyStatus> statuses
	    );
}

