package com.ey.dto.response;

import java.time.LocalDate;

import com.ey.enums.InstallmentType;
import com.ey.enums.PaymentStatus;

public class PremiumPaymentResponseDTO {
	private Long paymentId;
    private InstallmentType installmentType;
    private Double amount;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;
    private PaymentStatus status;
    private LocalDate paymentDate;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public InstallmentType getInstallmentType() {
		return installmentType;
	}
	public void setInstallmentType(InstallmentType installmentType) {
		this.installmentType = installmentType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getPeriodStartDate() {
		return periodStartDate;
	}
	public void setPeriodStartDate(LocalDate periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	public LocalDate getPeriodEndDate() {
		return periodEndDate;
	}
	public void setPeriodEndDate(LocalDate periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
    
    

}
