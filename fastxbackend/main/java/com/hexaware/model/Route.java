package com.hexaware.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Route {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int routeId;
	private String routeFrom;
	private String routeTo;
	private String boardingPoint;
	private String dropingPoint;
	
	//one route can be used by many busses
	@OneToMany(mappedBy="route")
	private List<Bus> bus;
	
	public Route() {
		
	}

	public Route(int routeId, String routeFrom, String routeTo, String boardingPoint, String dropingPoint,
			List<Bus> bus) {
		super();
		this.routeId = routeId;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.boardingPoint = boardingPoint;
		this.dropingPoint = dropingPoint;
		this.bus = bus;
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

	public List<Bus> getBus() {
		return bus;
	}

	public void setBus(List<Bus> bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return String.format("Route [routeId=%s, routeFrom=%s, routeTo=%s, boardingPoint=%s, dropingPoint=%s, bus=%s]",
				routeId, routeFrom, routeTo, boardingPoint, dropingPoint, bus);
	}
	
	
	
	
	
}