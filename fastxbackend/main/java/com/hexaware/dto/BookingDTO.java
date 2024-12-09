package com.hexaware.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class BookingDTO {
	
private int bookingId;
	
//	@NotBlank(message = "Booking status cannot be blank")
	private String bookingStatus;
	
	private LocalDate journeyDate;
	
	private LocalDate bookingDate;
	
	@NotBlank(message = "Seat information cannot be blank")
	private String seatInfo;
	
	private UserDTO user;
	private PaymentDTO payment;
	private BusDTO bus;
	
	
	
	public BookingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public BookingDTO(int bookingId, String bookingStatus, LocalDate journeyDate,
			LocalDate bookingDate, @NotBlank(message = "Seat information cannot be blank") String seatInfo,
			UserDTO user, PaymentDTO payment, BusDTO bus) {
		super();
		this.bookingId = bookingId;
		this.bookingStatus = bookingStatus;
		this.journeyDate = journeyDate;
		this.bookingDate = bookingDate;
		this.seatInfo = seatInfo;
		this.user = user;
		this.payment = payment;
		this.bus = bus;
	}





	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getSeatInfo() {
		return seatInfo;
	}
	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public PaymentDTO getPayment() {
		return payment;
	}
	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
	public BusDTO getBus() {
		return bus;
	}
	public void setBus(BusDTO bus) {
		this.bus = bus;
	}
	@Override
	public String toString() {
		return String.format("BookingDTO [bookingId=%s, bookingStatus=%s, journeyDate=%s, bookingDate=%s, seatInfo=%s]",
				bookingId, bookingStatus, journeyDate, bookingDate, seatInfo);
	}

	
	
	
}