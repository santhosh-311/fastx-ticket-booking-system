package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.OperatorNotAssociatedException;
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;

@Service
public interface BookingService {
	
	//user
	BookingDTO createBoooking(BookingDTO bookingDTO,String email,Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException, SeatNotAvailableException;
	BookingDTO cancelBooking(Integer bookingId) throws BookingNotFoundException, SeatNotFoundException;
	List<BookingDTO> myBookings(String email) throws UserNotFoundException, BookingNotFoundException;
	
	//user||operator||admin
	BookingDTO getBooking(Integer bookingId) throws BookingNotFoundException;
	
	//operator
	List<BookingDTO> viewBookings(String email,Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException;
	BookingDTO bookingCancellation(String email,Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException, SeatNotFoundException;
	
	//admin
	List<BookingDTO> allBookings() throws BookingNotFoundException;
}
