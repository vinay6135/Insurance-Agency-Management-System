package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;

public class CustomerPolicyRequestDTO {
	@NotNull
    private Long policyId;

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
}
