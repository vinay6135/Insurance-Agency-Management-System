package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.InstallmentType;
import com.ey.enums.PolicyStatus;

public class CustomerPolicyResponseDTO {
	
	private Long customerPolicyId;     
    private Long policyId;
    private String policyName;

    private PolicyStatus status;        

    private Double coverageAmount;
    private Double premiumAmount;

    private InstallmentType installmentType;
    private String agentPhone;

	public Long getCustomerPolicyId() {
		return customerPolicyId;
	}

	public void setCustomerPolicyId(Long customerPolicyId) {
		this.customerPolicyId = customerPolicyId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
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

	public Double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public Double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public InstallmentType getInstallmentType() {
		return installmentType;
	}

	public void setInstallmentType(InstallmentType installmentType) {
		this.installmentType = installmentType;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	
    
    
	    
	    

}
