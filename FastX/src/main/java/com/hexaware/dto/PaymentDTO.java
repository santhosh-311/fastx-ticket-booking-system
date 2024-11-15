package com.hexaware.dto;

import java.time.LocalDate;

public class PaymentDTO {
	private int paymentId;
	private double amountPaid;
	private LocalDate paymentDate;
	private String paymentStatus;
	private String refundStatus;
	private double refundAmount;
	private BookingDTO booking;
	private UserDTO user;
	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentDTO(int paymentId, double amountPaid, LocalDate paymentDate, String paymentStatus,
			String refundStatus, double refundAmount, BookingDTO booking, UserDTO user) {
		super();
		this.paymentId = paymentId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
		this.booking = booking;
		this.user = user;
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
	public BookingDTO getBooking() {
		return booking;
	}
	public void setBooking(BookingDTO booking) {
		this.booking = booking;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return String.format(
				"PaymentDTO [paymentId=%s, amountPaid=%s, paymentDate=%s, paymentStatus=%s, refundStatus=%s, refundAmount=%s, booking=%s, user=%s]",
				paymentId, amountPaid, paymentDate, paymentStatus, refundStatus, refundAmount, booking, user);
	}

	
}