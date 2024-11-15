package com.hexaware.dto;

import java.time.LocalDate;


public class BusDTO {

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
	
	private UserDTO user;
	
	private RouteDTO route;

	public BusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusDTO(int busId, int routeId, String busName, String busNumber, int availableSeats, int totalSeats,
			String busType, String amenities, String arrival, String departure, double pricePerSeat, LocalDate date,
			UserDTO user, RouteDTO route) {
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
		this.user = user;
		this.route = route;
	}

	@Override
	public String toString() {
		return String.format(
				"BusDTO [busId=%s, routeId=%s, busName=%s, busNumber=%s, availableSeats=%s, totalSeats=%s, busType=%s, amenities=%s, arrival=%s, departure=%s, pricePerSeat=%s, date=%s]",
				busId, routeId, busName, busNumber, availableSeats, totalSeats, busType, amenities, arrival, departure,
				pricePerSeat, date);
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public RouteDTO getRoute() {
		return route;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

}
