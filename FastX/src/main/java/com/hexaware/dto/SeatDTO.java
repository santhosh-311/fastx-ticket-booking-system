package com.hexaware.dto;

public class SeatDTO {
	private int seatId;
	private String availability;
	private int seatNumber;
	private BookingDTO booking;
	private BusDTO bus;
	
	public SeatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatDTO(int seatId, String availability, int seatNumber, BookingDTO booking, BusDTO bus) {
		super();
		this.seatId = seatId;
		this.availability = availability;
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

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
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
		return "SeatDTO [seatId=" + seatId + ", availability=" + availability + ", seatNumber=" + seatNumber
				+ ", booking=" + booking + ", bus=" + bus + "]";
	}
	
		
}