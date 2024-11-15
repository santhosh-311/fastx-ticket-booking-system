package com.hexaware.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.BusDTO;
import com.hexaware.exceptions.BusAlreadyExistException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.RouteNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.services.BusService;

@RestController
@RequestMapping("/api/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/add/{operatorId}")
	public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO, @PathVariable Integer operatorId ) throws BusAlreadyExistException{
		BusDTO bus = busService.addBus(busDTO, operatorId);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatedetails")
	public ResponseEntity<BusDTO> updateBusDetails(@RequestBody BusDTO busDTO) throws BusNotFoundException{
		BusDTO bus = busService.updateBusDetails(busDTO);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
	}
	
	@PutMapping("/updateroute")
	public ResponseEntity<BusDTO> updateBusRoutes(@RequestBody BusDTO busDTO, @PathVariable String routeFrom, @PathVariable String fromTo ) throws BusNotFoundException, RouteNotFoundException{
		BusDTO bus = busService.updateBusRoutes(busDTO, routeFrom, routeFrom);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deletebus(@PathVariable Integer BusId) throws BusNotFoundException{
		String s = busService.deleteBus(BusId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/searchBus/{operatorId}/{from}/{to}/{date}")
	public ResponseEntity<List<BusDTO>> searchBus(@PathVariable Integer operatorId, @PathVariable String from, @PathVariable String to, @PathVariable LocalDate date) throws UserNotFoundException, RouteNotFoundException, BusNotFoundException{
		List<BusDTO> busDTO = busService.searchBus(operatorId, from, to, date);
		return new ResponseEntity<List<BusDTO>>(busDTO,HttpStatus.OK);
	}
	
	@GetMapping("/searchBus/{from}/{to}/{date}")
	public ResponseEntity<List<BusDTO>> searchBus(@PathVariable String from, @PathVariable String to, @PathVariable LocalDate date) throws RouteNotFoundException, BusNotFoundException{
		List<BusDTO> busDTO = busService.searchBus(from, to, date);
		return new ResponseEntity<List<BusDTO>>(busDTO,HttpStatus.OK);
	}
	
	@GetMapping("/getbus/{busId}")
	public ResponseEntity<BusDTO> getBus(@PathVariable Integer busId) throws BusNotFoundException{
		BusDTO bus = busService.getBus(busId);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
	}

}




















