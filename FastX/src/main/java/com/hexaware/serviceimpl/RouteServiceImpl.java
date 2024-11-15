package com.hexaware.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.RouteDTO;
import com.hexaware.exceptions.*;
import com.hexaware.model.Route;
import com.hexaware.repositories.RouteRepository;

@Service
public class RouteServiceImpl {

    @Autowired
    private RouteRepository routeRepository;
    
    @Autowired
    private ModelMapper model;

    public Route addRoute(RouteDTO routeDTO) throws RouteAlreadyExistsException {
    	
    	Route route = routeRepository.findRoute(routeDTO.getRouteFrom(), routeDTO.getRouteTo());
    	if(route==null)
    		throw new RouteAlreadyExistsException("Route already exists");
    	
        return routeRepository.save(route);
    }

    public Route updateRoute(int routeId, Route routeDetails) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
        
        route.setRouteFrom(routeDetails.getRouteFrom());
        route.setRouteTo(routeDetails.getRouteTo());
        route.setBoardingPoint(routeDetails.getBoardingPoint());
        route.setDropingPoint(routeDetails.getDropingPoint());
        route.setBus(routeDetails.getBus());
        
        return routeRepository.save(route);
    }

    public Route getRouteById(int routeId) throws RouteNotFoundException {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public void deleteRoute(int routeId) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
        routeRepository.delete(route);
    }
}