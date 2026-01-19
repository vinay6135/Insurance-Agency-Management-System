package com.ey.dto.response;

public class PolicyResponseDTO {
	
	    private Long id;
	    private String policyName;
	    private Double coverageAmount;
	    private Double premiumAmount;
	    private Integer durationYears;
	    private boolean active;
	    private Long categoryid;
	    private String categoryName;
	    private String description;
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
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		
		public Long getCategoryid() {
			return categoryid;
		}
		public void setCategoryid(Long categoryid) {
			this.categoryid = categoryid;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	    
	    
	    
	    

}
