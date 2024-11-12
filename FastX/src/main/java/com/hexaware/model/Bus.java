package com.hexaware.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Bus {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int busId;
	private int routeId;
	private String busName;
	private String busNumber;
	private int availableSeats;
	private int totalSeats;
	private String busType;
	private String amenities;
	private String arrival;
	private String departure;
	private double pricePerSeat;
	private LocalDate date;
	
	//one bus can have multiple bookings
	@OneToMany(mappedBy="bus")
	private List<Booking> booking;
	
	//multiples busses will have one operator only
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	//many busses travel through same route
	@ManyToOne
	@JoinColumn(name="routeId")
	private Route route;
	
	//one bus will have many seats
	@OneToMany(mappedBy="bus")
	private List<Seat> seat;

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bus(int busId, int routeId, String busName, String busNumber, int availableSeats, int totalSeats,
			String busType, String amenities, String arrival, String departure, double pricePerSeat, LocalDate date,
			List<Booking> booking, User user, Route route, List<Seat> seat) {
		super();
		this.busId = busId;
		this.routeId = routeId;
		this.busName = busName;
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.totalSeats = totalSeats;
		this.busType = busType;
		this.amenities = amenities;
		this.arrival = arrival;
		this.departure = departure;
		this.pricePerSeat = pricePerSeat;
		this.date = date;
		this.booking = booking;
		this.user = user;
		this.route = route;
		this.seat = seat;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public double getPricePerSeat() {
		return pricePerSeat;
	}

	public void setPricePerSeat(double pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return String.format(
				"Bus [busId=%s, routeId=%s, busName=%s, busNumber=%s, availableSeats=%s, totalSeats=%s, busType=%s, amenities=%s, arrival=%s, departure=%s, pricePerSeat=%s, Date=%s, booking=%s, user=%s, route=%s, seat=%s]",
				busId, routeId, busName, busNumber, availableSeats, totalSeats, busType, amenities, arrival, departure,
				pricePerSeat, date, booking, user, route, seat);
	}
	
	
	
}
