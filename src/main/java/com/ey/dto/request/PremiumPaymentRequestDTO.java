package com.ey.dto.request;

import com.ey.enums.InstallmentType;

import jakarta.validation.constraints.NotNull;

public class PremiumPaymentRequestDTO {
    @NotNull
    private Long customerPolicyId;

    @NotNull
    private InstallmentType installmentType;

	public Long getCustomerPolicyId() {
		return customerPolicyId;
	}

	public void setCustomerPolicyId(Long customerPolicyId) {
		this.customerPolicyId = customerPolicyId;
	}

	public InstallmentType getInstallmentType() {
		return installmentType;
	}

	public void setInstallmentType(InstallmentType installmentType) {
		this.installmentType = installmentType;
	}
    
    

}
