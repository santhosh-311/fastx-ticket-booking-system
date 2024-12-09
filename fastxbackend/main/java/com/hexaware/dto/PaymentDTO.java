package com.hexaware.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class PaymentDTO {
	private int paymentId;
	
	@Positive(message = "Amount paid must be a positive value")
	private double amountPaid;
	
	private LocalDate paymentDate;
	 
	private String paymentStatus;
	 
	private String refundStatus;
	 
	@Min(value = 0, message = "Refund amount cannot be negative")
	private double refundAmount;
	
	
	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentDTO(int paymentId, double amountPaid, LocalDate paymentDate, String paymentStatus,
			String refundStatus, double refundAmount) {
		super();
		this.paymentId = paymentId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	@Override
	public String toString() {
		return String.format(
				"PaymentDTO [paymentId=%s, amountPaid=%s, paymentDate=%s, paymentStatus=%s, refundStatus=%s, refundAmount=%s]",
				paymentId, amountPaid, paymentDate, paymentStatus, refundStatus, refundAmount);
	}

	
}