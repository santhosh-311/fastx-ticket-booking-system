package com.hexaware.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Payment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentId;
	private double amountPaid;
	private LocalDate paymentDate;
	private String paymentStatus;
	private String refundStatus;
	private double refundAmount;
	
	//many payments can be done by same user(one user)
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	
	
	//one payment is associated with one booking
	@OneToOne
	@JoinColumn(name="bookingId")
	private Booking booking;


	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Payment(int paymentId, double amountPaid, LocalDate paymentDate, String paymentStatus,
			String refundStatus, double refundAmount, Users user, Booking booking) {
		super();
		this.paymentId = paymentId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
		this.user = user;
		this.booking = booking;
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


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	@Override
	public String toString() {
		return String.format(
				"Payment [paymentId=%s, amountPaid=%s, paymentDate=%s, paymentStatus=%s, refundStatus=%s, refundAmount=%s, user=%s, booking=%s]",
				paymentId, amountPaid, paymentDate, paymentStatus, refundStatus, refundAmount, user,
				booking);
	}
	
	
}