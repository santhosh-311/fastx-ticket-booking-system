package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
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
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.services.BookingService;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/user/create/{userName}/{busId}")
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO,
			@PathVariable String userName, @PathVariable Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException, SeatNotAvailableException
	{
		BookingDTO booking = bookingService.createBoooking(bookingDTO, userName, busId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.CREATED);
	}
	
	@PutMapping("/user/cancel/{bookingId}")
	public ResponseEntity<BookingDTO> cancelBooking(@PathVariable Integer bookingId) throws BookingNotFoundException, SeatNotFoundException{
		BookingDTO booking = bookingService.cancelBooking(bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/mybookings/{userName}")
	public ResponseEntity<List<BookingDTO>> myBookings(@PathVariable String userName) throws UserNotFoundException, BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.myBookings(userName);
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
	
	@GetMapping("/getbooking/{bookingId}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer bookingId) throws BookingNotFoundException{
		BookingDTO booking = bookingService.getBooking(bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.OK);
	}
	
	@GetMapping("/op/viewbookings/{userName}/{busId}")
	public ResponseEntity<List<BookingDTO>> viewBookings(@PathVariable String userName, @PathVariable Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.viewBookings(userName, busId);
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
	
	@PutMapping("/op/cancel/{userName}/{bookingId}")
	public ResponseEntity<BookingDTO> bookingCancellation(@PathVariable String userName, @PathVariable Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException, SeatNotFoundException{
		BookingDTO booking = bookingService.bookingCancellation(userName, bookingId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.OK);
	}
	
	@GetMapping("/admin/allbookings")
	public ResponseEntity<List<BookingDTO>> allBookings() throws BookingNotFoundException{
		List<BookingDTO> bookingList = bookingService.allBookings();
		return new ResponseEntity<List<BookingDTO>>(bookingList,HttpStatus.OK);
	}
}
