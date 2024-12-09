package com.hexaware.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;


public class BusDTO {

	private int busId;
	
	@NotBlank(message = "Bus name cannot be blank")
    @Size(max = 50, message = "Bus name cannot exceed 50 characters")
	private String busName;
	
	@NotBlank(message = "Bus number cannot be blank")
    @Pattern(regexp = "^[A-Z0-9-]{10,15}$", message = "Bus number must be alphanumeric and 5-15 characters long")
	private String busNumber;
	
	@Min(value = 0, message = "Available seats cannot be negative")
    @Max(value = Integer.MAX_VALUE, message = "Available seats exceeds the maximum limit")
	private int availableSeats;
	
	@Min(value = 0, message = "Total seats must be at least 0")
	@Max(value = 30, message ="Total seats cannot exceed 30")
	private int totalSeats;
	
	@NotBlank(message = "Bus type cannot be blank")
    @Pattern(regexp = "AC_SLEEPER|NON_AC SLEEPER|AC_SEATER|NON_AC_SEATER",  message = "Bus type must be either AC(SEATER/SLEEPER) NON_AC(SEATER/SLEEPER)")
	private String busType;
	
	@NotBlank(message = "Amenities cannot be blank")
    @Size(max = 100, message = "Amenities cannot exceed 100 characters")
	private String amenities;
	
	@NotNull(message = "Arrival time cannot be null")
	private LocalTime arrival;
	
	@NotNull(message = "Departure time cannot be null")
	private LocalTime departure;
	
	@Positive(message = "Price per seat must be positive")
	private double pricePerSeat;
	
	@NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date must be today or in the future")
	private LocalDate date;
	
	
	private UserDTO user;
	
	private RouteDTO route;

	public BusDTO() {
	}

	public BusDTO(int busId, String busName, String busNumber, int availableSeats, int totalSeats,
			String busType, String amenities, LocalTime arrival, LocalTime departure, double pricePerSeat, LocalDate date,
			UserDTO user, RouteDTO route) {
		super();
		this.busId = busId;
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
				"BusDTO [busId=%s, busName=%s, busNumber=%s, availableSeats=%s, totalSeats=%s, busType=%s, amenities=%s, arrival=%s, departure=%s, pricePerSeat=%s, date=%s]",
				busId, busName, busNumber, availableSeats, totalSeats, busType, amenities, arrival, departure,
				pricePerSeat, date);
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
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

	public LocalTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
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