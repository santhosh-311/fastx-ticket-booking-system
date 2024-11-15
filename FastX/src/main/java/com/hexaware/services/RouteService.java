package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.RouteDTO;
import com.hexaware.model.Route;

@Service
public interface RouteService {
	Route addRoute(RouteDTO routeDTO);
	Route updateRoute(int id, Route routeDetails);
	Route getRouteById(int id);
	List<Route> getAllRoutes();
	void deleteRoute(int id);
}