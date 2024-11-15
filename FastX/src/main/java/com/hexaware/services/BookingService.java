package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.OperatorNotAssociatedException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;

@Service
public interface BookingService {
	
	//user
	BookingDTO createBoooking(BookingDTO bookingDTO,Integer userId,Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException;
	BookingDTO cancelBooking(Integer bookingId) throws BookingNotFoundException, SeatNotFoundException;
	List<BookingDTO> myBookings(Integer userId) throws UserNotFoundException, BookingNotFoundException;
	
	//user||operator||admin
	BookingDTO getBooking(Integer bookingId) throws BookingNotFoundException;
	
	//operator
	List<BookingDTO> viewBookings(Integer operatorId,Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException;
	BookingDTO bookingCancellation(Integer operatorId,Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException;
	
	//admin
	List<BookingDTO> allBookings() throws BookingNotFoundException;
}
