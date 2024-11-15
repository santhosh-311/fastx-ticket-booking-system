package com.hexaware.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.dto.RouteDTO;
import com.hexaware.exceptions.*;
import com.hexaware.model.Route;
import com.hexaware.services.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Route> addRoute(@RequestBody RouteDTO route) {
    	
        Route newRoute = routeService.addRoute(route);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable int id, @RequestBody Route routeDetails) throws RouteNotFoundException {
        Route updatedRoute = routeService.updateRoute(id, routeDetails);
		return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable int id) throws RouteNotFoundException {
        Route route = routeService.getRouteById(id);
		return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping("/getall/{id}")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable int id) throws RouteNotFoundException {
        routeService.deleteRoute(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}