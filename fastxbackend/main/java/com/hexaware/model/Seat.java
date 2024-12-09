package com.hexaware.model;

import jakarta.persistence.*;

@Entity
public class Seat {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

	private int seatId;
	private boolean available;
	private String seatNumber;
	
	//multiple seats can be booked for one booking
	@ManyToOne
	@JoinColumn(name="bookingId")
	private Booking booking;
	
	//multiple seats can be there in one bus
	@ManyToOne
	@JoinColumn(name="busId")
	private Bus bus;
	
	
	public Seat() {
		
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


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	public Bus getBus() {
		return bus;
	}


	public void setBus(Bus bus) {
		this.bus = bus;
	}


	public Seat(int seatId, boolean available, String seatNumber, Booking booking, Bus bus) {
		super();
		this.seatId = seatId;
		this.available = available;
		this.seatNumber = seatNumber;
		this.booking = booking;
		this.bus = bus;
	}


	@Override
	public String toString() {
		return String.format("Seat [seatId=%s, available=%s, seatNumber=%s, booking=%s, bus=%s]", seatId, available,
				seatNumber, booking, bus);
	}


	
	
	
	
}