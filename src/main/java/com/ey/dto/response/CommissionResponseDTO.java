package com.ey.dto.response;

public class CommissionResponseDTO {
	
	private Long premiumId;
	private double amount;
	private long agentId;
	private double commision;
	public Long getPremiumId() {
		return premiumId;
	}
	public void setPremiumId(Long premiumId) {
		this.premiumId = premiumId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public long getAgentId() {
		return agentId;
	}
	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	

}
