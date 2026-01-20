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


	    
	    
	


}
