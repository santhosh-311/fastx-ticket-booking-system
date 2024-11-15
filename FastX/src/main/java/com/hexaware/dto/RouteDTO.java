package com.hexaware.dto;

public class RouteDTO {
	private int routeId;
	private String routeFrom;
	private String routeTo;
	private String boardingPoint;
	private String droppingPoint;


	public RouteDTO() {}

	public RouteDTO(int routeId, String routeFrom, String routeTo, String boardingPoint, String droppingPoint) {
		this.routeId = routeId;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.boardingPoint = boardingPoint;
		this.droppingPoint = droppingPoint;
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

	public String getDroppingPoint() {
		return droppingPoint;
	}

	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
	}

	@Override
	public String toString() {
		return String.format(
				"RouteDTO [routeId=%s, routeFrom=%s, routeTo=%s, boardingPoint=%s, droppingPoint=%s]",
				routeId, routeFrom, routeTo, boardingPoint, droppingPoint);
	}
}