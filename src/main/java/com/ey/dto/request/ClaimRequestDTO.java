package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClaimRequestDTO {
    @NotNull
    private Long customerPolicyId;

    @NotBlank
    private String reason;

	public Long getCustomerPolicyId() {
		return customerPolicyId;
	}

	public void setCustomerPolicyId(Long customerPolicyId) {
		this.customerPolicyId = customerPolicyId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
