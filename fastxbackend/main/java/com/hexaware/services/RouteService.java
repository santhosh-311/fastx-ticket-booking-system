package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.RouteDTO;
import com.hexaware.exceptions.RouteAlreadyExistsException;
import com.hexaware.exceptions.RouteNotFoundException;
import com.hexaware.model.Route;

@Service
public interface RouteService {
	RouteDTO addRoute(RouteDTO routeDTO) throws RouteAlreadyExistsException;
	RouteDTO updateRoute(int id, RouteDTO routeDetails) throws RouteNotFoundException;
	RouteDTO getRouteById(int id) throws RouteNotFoundException;
	List<RouteDTO> getAllRoutes() throws RouteNotFoundException;
	String deleteRoute(int id) throws RouteNotFoundException;
}