package com.hexaware.dto;

import jakarta.validation.constraints.*;

public class SeatDTO {
	private int seatId;
	
	@NotNull(message = "Availability status must be specified")
	private boolean available;
	
	@NotBlank(message = "Seat number cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]{1,10}$", message = "Seat number must be alphanumeric and up to 10 characters long")
	private String seatNumber;
	
	private BookingDTO booking;
	private BusDTO bus;
	
	
	public SeatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatDTO(int seatId, boolean available, String seatNumber, BookingDTO booking, BusDTO bus) {
		super();
		this.seatId = seatId;
		this.available = available;
		this.seatNumber = seatNumber;
		this.booking = booking;
		this.bus = bus;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public BookingDTO getBooking() {
		return booking;
	}

	public void setBooking(BookingDTO booking) {
		this.booking = booking;
	}

	public BusDTO getBus() {
		return bus;
	}

	public void setBus(BusDTO bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return String.format("SeatDTO [seatId=%s, available=%s, seatNumber=%s, booking=%s, bus=%s]", seatId, available,
				seatNumber, booking, bus);
	}

	
	
		
}