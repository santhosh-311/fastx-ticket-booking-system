package com.hexaware.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.model.Seat;
import com.hexaware.services.SeatService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/seat")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private ModelMapper model;
	
	@PutMapping("/user/book/{busId}/{seatNo}")
	public ResponseEntity<SeatDTO> bookSeat(@PathVariable Integer busId, @PathVariable String seatNo) throws BusNotFoundException, SeatNotFoundException, SeatNotAvailableException{
		Seat seat=seatService.bookSeat(busId, seatNo);
		
		return new ResponseEntity<SeatDTO>(model.map(seat, SeatDTO.class),HttpStatus.OK);
	}
	
	@PutMapping("/user/cancel")
	public ResponseEntity<String> cancelSeat(@RequestBody SeatDTO seatDTO) throws SeatNotFoundException{
		String s = seatService.cancelSeat(seatDTO);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
//	@PostMapping("/add/{busId}")
//	public ResponseEntity<SeatDTO> addSeat(@RequestBody SeatDTO seatDTO, @PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
//		SeatDTO seat = seatService.addSeat(seatDTO, busId);
//		return new ResponseEntity<SeatDTO>(seat,HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/remove/{busId}/{seatNo}")
//	public ResponseEntity<String> removeSeat(@PathVariable Integer busId, @PathVariable String seatNo) throws SeatNotFoundException, BusNotFoundException{
//		String s = seatService.removeSeat(busId, seatNo);
//		return new ResponseEntity<String>(s,HttpStatus.OK);
//	}
	
	@GetMapping("/getseats/{busId}")
	public ResponseEntity<List<SeatDTO>> getSeats(@PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
		List<SeatDTO> seatList = seatService.getSeats(busId);
		return new ResponseEntity<List<SeatDTO>>(seatList,HttpStatus.OK);
	}
	
	@PostMapping("/op/addseats/{seatNumbers}/{busId}")
	public ResponseEntity<List<SeatDTO>> addSeats(@PathVariable String seatNumbers, @PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
		List<SeatDTO> seatList = seatService.addMultlipleSeats(seatNumbers, busId);
		return new ResponseEntity<List<SeatDTO>>(seatList,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/op/removeseats/{seatNumbers}/{busId}")
	public ResponseEntity<String> removeSeats(@PathVariable String seatNumbers, @PathVariable Integer busId) throws BusNotFoundException, SeatNotFoundException{
		String s = seatService.removeMultipleSeats(seatNumbers, busId);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	
	@PutMapping("op/block/{busId}/{seatNumbers}")
	public ResponseEntity<String> blockSeats(@PathVariable Integer busId,@PathVariable String seatNumbers) throws BusNotFoundException,SeatNotFoundException{
		String s = seatService.blockSeats(busId, seatNumbers);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	
	@PutMapping("op/unblock/{busId}/{seatNumbers}")
	public ResponseEntity<String> unblockSeats(@PathVariable Integer busId,@PathVariable String seatNumbers) throws BusNotFoundException,SeatNotFoundException{
		String s = seatService.unblockSeats(busId, seatNumbers);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}

	
}
