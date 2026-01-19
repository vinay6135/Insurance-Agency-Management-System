package com.ey.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class UpdatePolicyRequestDTO {
	
	    @Positive
	    private Double coverageAmount;

	    @Positive
	    private Double premiumAmount;

	    @Min(1)
	    private Integer durationYears;

	    private Boolean active;

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

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
		}
	    
	    
	


}
