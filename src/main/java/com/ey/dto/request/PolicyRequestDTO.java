package com.ey.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PolicyRequestDTO {
	@NotBlank(message = "Policy name is required")
    private String policyName;

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be greater than 0")
    private Double coverageAmount;

    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be greater than 0")
    private Double premiumAmount;

    @NotNull(message = "Policy duration is required")
    @Min(value = 1, message = "Minimum policy duration is 1 year")
    private Integer durationYears;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
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

	public Integer getDurationYears() {
		return durationYears;
	}

	public void setDurationYears(Integer durationYears) {
		this.durationYears = durationYears;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
       

}