package com.hexaware.dto;

import jakarta.validation.constraints.*;

public class RouteDTO {

	private int routeId;
	
	@NotBlank(message = "Route from location cannot be blank")
    @Size(max = 50, message = "Route from location cannot exceed 50 characters")
	private String routeFrom;
	
	@NotBlank(message = "Route to location cannot be blank")
    @Size(max = 50, message = "Route to location cannot exceed 50 characters")
	private String routeTo;
	
	@NotBlank(message = "Boarding point cannot be blank")
    @Size(max = 50, message = "Boarding point cannot exceed 50 characters")
	private String boardingPoint;
	
	@NotBlank(message = "Dropping point cannot be blank")
    @Size(max = 50, message = "Dropping point cannot exceed 50 characters")
	private String dropingPoint;
	public RouteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RouteDTO(int routeId, String routeFrom, String routeTo, String boardingPoint, String dropingPoint) {
		super();
		this.routeId = routeId;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.boardingPoint = boardingPoint;
		this.dropingPoint = dropingPoint;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getRouteFrom() {
		return routeFrom;
	}
	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}
	public String getRouteTo() {
		return routeTo;
	}
	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDropingPoint() {
		return dropingPoint;
	}
	public void setDropingPoint(String dropingPoint) {
		this.dropingPoint = dropingPoint;
	}
	@Override
	public String toString() {
		return String.format("RouteDTO [routeId=%s, routeFrom=%s, routeTo=%s, boardingPoint=%s, dropingPoint=%s]",
				routeId, routeFrom, routeTo, boardingPoint, dropingPoint);
	}
	
	
}
