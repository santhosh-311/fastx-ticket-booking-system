package com.hexaware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.BookingDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.OperatorNotAssociatedException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.services.BookingService;


@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/createbooking/{userId}/{busId}")
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO,
			@PathVariable Integer userId, @PathVariable Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException
	{
		BookingDTO booking = bookingService.createBoooking(bookingDTO, userId, busId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.CREATED);
	}
	
	@PutMapping("/cancelbooking/{bookingId}")
	public ResponseEntity<BookingDTO> cancelBooking(@PathVariable Integer bookingId) throws BookingNotFoundException, SeatNotFoundException{
		BookingDTO booking = bookingService.cancelBooking(bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/mybookings/{userId}")
	public ResponseEntity<List<BookingDTO>> myBookings(@PathVariable Integer userId) throws UserNotFoundException, BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.myBookings(userId);
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
	
	@GetMapping("/getbooking/{bookingId}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer bookingId) throws BookingNotFoundException{
		BookingDTO booking = bookingService.getBooking(bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.OK);
	}
	
	@GetMapping("/viewbookings/{operatorId}/{busId}")
	public ResponseEntity<List<BookingDTO>> viewBookings(@PathVariable Integer operatorId, @PathVariable Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.viewBookings(operatorId, busId);
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
	
	@PutMapping("/bookingcancellation/{operatorId}/{bookingId}")
	public ResponseEntity<BookingDTO> bookingCancellation(@PathVariable Integer operatorId, @PathVariable Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException{
		BookingDTO booking = bookingService.bookingCancellation(operatorId, bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.OK);
	}
	
	@GetMapping("/allbookings")
	public ResponseEntity<List<BookingDTO>> allBookings() throws BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.allBookings();
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
}
