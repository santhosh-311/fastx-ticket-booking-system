package com.hexaware.controller;

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
@CrossOrigin("http://localhost:3000")
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/admin/add")
    public ResponseEntity<RouteDTO> addRoute(@RequestBody RouteDTO route) throws RouteAlreadyExistsException {
    	
        RouteDTO newRoute = routeService.addRoute(route);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable int id, @RequestBody RouteDTO routeDetails) throws RouteNotFoundException {
        RouteDTO updatedRoute = routeService.updateRoute(id, routeDetails);
		return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @GetMapping("/admin/getroute/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable int id) throws RouteNotFoundException {
        RouteDTO route = routeService.getRouteById(id);
		return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() throws RouteNotFoundException {
        List<RouteDTO> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable int id) throws RouteNotFoundException {
        String s=routeService.deleteRoute(id);
		return new ResponseEntity<>(s,HttpStatus.OK);
    }
}