package com.hexaware.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Booking {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	private String bookingStatus;
	private LocalDate jouneryDate;
	private LocalDate bookingDate;
	private String seatInfo;
	
	//multiple bookings can be done by one user
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	//one booking will be done by one payment only
	@OneToOne
	private Payment payment;
	
	//one booking can have multiple seats
	@OneToMany(mappedBy="booking")
	private List<Seat> seat=new ArrayList<>();
	
	//multiple bookings can be made to one bus
	@ManyToOne
	@JoinColumn(name="busId")
	private Bus bus;

	public Booking(int bookingId, String bookingStatus, LocalDate jouneryDate, LocalDate bookingDate, String seatInfo,
			User user, Payment payment, List<Seat> seat, Bus bus) {
		super();
		this.bookingId = bookingId;
		this.bookingStatus = bookingStatus;
		this.jouneryDate = jouneryDate;
		this.bookingDate = bookingDate;
		this.seatInfo = seatInfo;
		this.user = user;
		this.payment = payment;
		this.seat = seat;
		this.bus = bus;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
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

	public LocalDate getJouneryDate() {
		return jouneryDate;
	}

	public void setJouneryDate(LocalDate jouneryDate) {
		this.jouneryDate = jouneryDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return String.format(
				"Booking [bookingId=%s, bookingStatus=%s, jouneryDate=%s, bookingDate=%s, seatInfo=%s, user=%s, payment=%s, seat=%s, bus=%s]",
				bookingId, bookingStatus, jouneryDate, bookingDate, seatInfo, user, payment, seat, bus);
	}
	
	
	
}
