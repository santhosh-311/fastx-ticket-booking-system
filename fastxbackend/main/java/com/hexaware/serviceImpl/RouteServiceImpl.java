package com.hexaware.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.RouteDTO;
import com.hexaware.exceptions.*;
import com.hexaware.model.Route;
import com.hexaware.repositories.RouteRepository;
import com.hexaware.services.RouteService;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;
    
    @Autowired
    private ModelMapper model;

    public RouteDTO addRoute(RouteDTO routeDTO) throws RouteAlreadyExistsException {
    	
    	System.out.println("DTO:"+routeDTO);
    	Route route = routeRepository.findRoute(routeDTO.getRouteFrom(), routeDTO.getRouteTo());
    	if(route!=null)
    		throw new RouteAlreadyExistsException("Route already exists");
    	route=model.map(routeDTO, Route.class);
    	
    	System.out.println("After mapping: "+route);
    	route= routeRepository.save(route);
    	
    	return model.map(route, RouteDTO.class);
    }

    public RouteDTO updateRoute(int routeId, RouteDTO routeDetails) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
        
        route.setRouteFrom(routeDetails.getRouteFrom());
        route.setRouteTo(routeDetails.getRouteTo());
        route.setBoardingPoint(routeDetails.getBoardingPoint());
        route.setDropingPoint(routeDetails.getDropingPoint());
        
        route =routeRepository.save(route);
        return model.map(route, RouteDTO.class);
    }

    public RouteDTO getRouteById(int routeId) throws RouteNotFoundException {
        Route route=routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
        return model.map(route, RouteDTO.class);
        
    }

    public List<RouteDTO> getAllRoutes() throws RouteNotFoundException {
        List<Route> routes=routeRepository.findAll();
        if(routes.isEmpty())
        	throw new RouteNotFoundException("No Routes are available");
        return routes.stream()
        		.map(route-> model.map(route, RouteDTO.class))
        		.collect(Collectors.toList());
        
    }

    public String deleteRoute(int routeId) throws RouteNotFoundException {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found for id: " + routeId));
        routeRepository.delete(route);
        return "Route Deleted";
    }
}