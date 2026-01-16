package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.PolicyStatus;

public class CustomerPolicyResponseDTO {
	
	    private Long id;
	    private String policyName;
	    private PolicyStatus status;
	    private String agentName;
	    private LocalDateTime createdAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPolicyName() {
			return policyName;
		}
		public void setPolicyName(String policyName) {
			this.policyName = policyName;
		}
		public PolicyStatus getStatus() {
			return status;
		}
		public void setStatus(PolicyStatus status) {
			this.status = status;
		}
		public String getAgentName() {
			return agentName;
		}
		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}	    

}
