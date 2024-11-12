package com.hexaware.model;

import jakarta.persistence.*;

@Entity
public class Seat {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

	private int seatId;
	private String availability;
	private int seatNumber;
	
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


	public Seat(int seatId, String availability, int seatNumber, Booking booking, Bus bus) {
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


	@Override
	public String toString() {
		return String.format("Seat [seatId=%s, availability=%s, seatNumber=%s, booking=%s, bus=%s]", seatId,
				availability, seatNumber, booking, bus);
	}
	
	
	
	
}
