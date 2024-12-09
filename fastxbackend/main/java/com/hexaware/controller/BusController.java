package com.hexaware.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:3000")
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/op/add/{routeId}/{userName}")
	public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO,@PathVariable Integer routeId, @PathVariable String userName ) throws BusAlreadyExistException, UserNotFoundException, RouteNotFoundException{
		BusDTO bus = busService.addBus(busDTO, routeId,userName);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.CREATED);
	}
	
	@PutMapping("/op/update/{routeId}")
	public ResponseEntity<BusDTO> updateBusDetails(@RequestBody BusDTO busDTO, @PathVariable Integer routeId) throws BusNotFoundException,RouteNotFoundException{
		BusDTO bus = busService.updateBusDetails(busDTO,routeId);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
	}
	
//	@PutMapping("/op/updateroute")
//	public ResponseEntity<BusDTO> updateBusRoutes(@RequestBody BusDTO busDTO, @PathVariable String routeFrom, @PathVariable String fromTo ) throws BusNotFoundException, RouteNotFoundException{
//		BusDTO bus = busService.updateBusRoutes(busDTO, routeFrom, routeFrom);
//		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
//	}
	
	@DeleteMapping("/op/delete/{busId}")
	public ResponseEntity<String> deletebus(@PathVariable Integer busId) throws BusNotFoundException{
		String s = busService.deleteBus(busId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/op/searchbus/{userName}/{from}/{to}/{date}")
	public ResponseEntity<List<BusDTO>> searchBus(@PathVariable String userName, @PathVariable String from, @PathVariable String to, @PathVariable LocalDate date) throws UserNotFoundException, RouteNotFoundException, BusNotFoundException{
		List<BusDTO> busDTO = busService.searchBus(userName, from, to, date);
		return new ResponseEntity<List<BusDTO>>(busDTO,HttpStatus.OK);
	}
	
	@GetMapping("/op/getbus/{userName}")
	public ResponseEntity<List<BusDTO>> searchBus(@PathVariable String userName) throws UserNotFoundException{
		List<BusDTO> busDTO = busService.getBus(userName);
		return new ResponseEntity<List<BusDTO>>(busDTO,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/searchbus/{from}/{to}/{date}/{bustype}")
	public ResponseEntity<List<BusDTO>> searchBus(@PathVariable String from, @PathVariable String to, @PathVariable LocalDate date,@PathVariable String bustype) throws RouteNotFoundException, BusNotFoundException{

		List<BusDTO> busDTO = busService.searchBus(from, to, date,bustype);
		return new ResponseEntity<List<BusDTO>>(busDTO,HttpStatus.OK);
	}
	
	@GetMapping("/getbus/{busId}")
	public ResponseEntity<BusDTO> getBus(@PathVariable Integer busId) throws BusNotFoundException{
		BusDTO bus = busService.getBus(busId);
		return new ResponseEntity<BusDTO>(bus,HttpStatus.OK);
	}

}




















