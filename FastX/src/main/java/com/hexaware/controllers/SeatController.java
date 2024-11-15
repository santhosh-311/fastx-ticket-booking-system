package com.hexaware.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.services.SeatService;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@PutMapping("/bookseat/{busId}/{seatNo}")
	public ResponseEntity<SeatDTO> bookSeat(@PathVariable Integer busId, @PathVariable String seatNo) throws BusNotFoundException, SeatNotFoundException{
		SeatDTO seat=seatService.bookSeat(busId, seatNo);
		return new ResponseEntity<SeatDTO>(seat,HttpStatus.OK);
	}
	
	@PutMapping("/cancelseat")
	public ResponseEntity<String> cancelSeat(@RequestBody SeatDTO seatDTO) throws SeatNotFoundException{
		String s = seatService.cancelSeat(seatDTO);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@PostMapping("/addseat/{busId}")
	public ResponseEntity<SeatDTO> addSeat(@RequestBody SeatDTO seatDTO, @PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
		SeatDTO seat = seatService.addSeat(seatDTO, busId);
		return new ResponseEntity<SeatDTO>(seat,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeseat")
	public ResponseEntity<String> removeSeat(@PathVariable Integer seatId) throws SeatNotFoundException{
		String s = seatService.removeSeat(seatId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/getseats/{busId}")
	public ResponseEntity<List<SeatDTO>> getSeats(@PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
		List<SeatDTO> seatList = seatService.getSeats(busId);
		return new ResponseEntity<List<SeatDTO>>(seatList,HttpStatus.OK);
	}
}
