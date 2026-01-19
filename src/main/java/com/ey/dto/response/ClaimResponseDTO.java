package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.ClaimStatus;

public class ClaimResponseDTO {
	private Long claimId;
    private ClaimStatus status;
    private String policyName;
    private Long customerpolicyId;
    private Long customerId;
    private LocalDateTime createdAt;
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public ClaimStatus getStatus() {
		return status;
	}
	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	public Long getCustomerpolicyId() {
		return customerpolicyId;
	}
	public void setCustomerpolicyId(Long customerpolicyId) {
		this.customerpolicyId = customerpolicyId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    

}
