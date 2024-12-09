package com.hexaware.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.dto.BusDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.OperatorNotAssociatedException;
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.dto.SeatDTO;
import com.hexaware.model.Booking;
import com.hexaware.model.Bus;
import com.hexaware.model.Payment;
import com.hexaware.model.Seat;
import com.hexaware.model.Users;
import com.hexaware.repositories.BookingRepository;
import com.hexaware.repositories.BusRepository;
import com.hexaware.repositories.SeatRepository;
import com.hexaware.repositories.UserRepository;
import com.hexaware.services.BookingService;
import com.hexaware.services.SeatService;


@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BusRepository busRepo;
	@Autowired
	private SeatRepository seatRepo;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private ModelMapper model;
	@Override
	public BookingDTO createBoooking(BookingDTO bookingDTO, String email, Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException, SeatNotAvailableException {
		//checking for user,and bus
		Users user = userRepo.findByEmail(email);
		if(user==null)
			throw new UserNotFoundException("User Not Found "+email);
			
		Bus bus= busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		
		//creating a booking object
		Booking booking = model.map(bookingDTO, Booking.class);
		booking.setBookingStatus("booking");
		booking = bookingRepo.save(booking);
		List<String> seatNo= new ArrayList<>(Arrays.asList(booking.getSeatInfo().split(",")));           
		
		//updates seats
		List<Seat> seats= new ArrayList<>();
		
		for(String i:seatNo) {
			Seat seat=seatService.bookSeat(busId,i);
			seat.setBooking(booking);
			seat=seatRepo.save(seat);
			seats.add(seat);
		}
		
		
		//setting seat relations
		booking.setSeat(seats);
		
		//setting relations
		booking.setUser(user);
		booking.setBus(bus);
		bus.getBooking().add(booking);
		user.getBooking().add(booking);
		booking.setBookingStatus("payment pending");
		booking.setBookingDate(LocalDate.now());
		booking.setJourneyDate(bus.getDate());
		
		
		//saving to the repository and taking its address
		bus.setAvailableSeats(bus.getAvailableSeats()-seats.size());
		busRepo.save(bus);
		booking=bookingRepo.save(booking);
		return model.map(booking, BookingDTO.class);
	}
	
	@Override
	public BookingDTO cancelBooking(Integer bookingId) throws BookingNotFoundException, SeatNotFoundException {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		booking.setBookingStatus("Refund Pending");
		for(Seat i:booking.getSeat()) {
			seatService.cancelSeat(model.map(i, SeatDTO.class));
		}
		Bus bus =booking.getBus();
		bus.setAvailableSeats(bus.getAvailableSeats()+booking.getSeat().size());
		
		busRepo.save(bus);
		
		booking.setSeat(null);
		booking = bookingRepo.save(booking);
		
		
		BookingDTO bookingDTO =model.map(booking, BookingDTO.class);
		
		return bookingDTO;
	}
	
	@Override
	public List<BookingDTO> myBookings(String email) throws UserNotFoundException, BookingNotFoundException{
		
		Users user=userRepo.findByEmail(email);
		if(user==null)
			throw new UserNotFoundException("User Not Found "+email);
		List<Booking> bookings = bookingRepo.findByUser(user);
		if(bookings.isEmpty())
			throw new BookingNotFoundException("Bookings with userId :"+user.getUserId()+" Not Found");
		List<BookingDTO> bookingList = bookings.stream()
                .map(booking -> model.map(booking, BookingDTO.class))
                .collect(Collectors.toList());	
		return bookingList;
	}

	@Override
	public BookingDTO getBooking(Integer bookingId) throws BookingNotFoundException {
		
		Booking booking =bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		BookingDTO bookingDTO=model.map(booking, BookingDTO.class);
		return bookingDTO;
	}

	@Override
	public List<BookingDTO> viewBookings(String email,Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException {
		
		Users operator = userRepo.findByEmail(email);
		if(operator==null)
			throw new UserNotFoundException("Operator Not Found "+email);

		Bus bus=busRepo.findById(busId).orElseThrow(()->new BusNotFoundException("Bus :"+busId+" Not Found"));
		if(bus.getUser().getUserId()!=operator.getUserId())
			throw new OperatorNotAssociatedException("Operator :"+operator.getEmail()+" is not associated with Bus :"+bus.getBusId());
		
		List<Booking> bookings = bookingRepo.findByBus(bus);
		if(bookings.isEmpty())
			throw new BookingNotFoundException("Bookings with Bus: "+busId+"Not Found");
		List<BookingDTO> bookingList = bookings.stream()
                .map(booking -> model.map(booking, BookingDTO.class))
                .collect(Collectors.toList());			
		
		return bookingList;
	}

	@Override
	public BookingDTO bookingCancellation(String email,Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException, SeatNotFoundException {
		BookingDTO savedBookingDTO=null;
		Users operator = userRepo.findByEmail(email);
		if(operator==null)
			throw new UserNotFoundException("Operator Not Found "+email);
		Booking booking=bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		Bus bus=booking.getBus();
		
		if(bus.getUser().getUserId()!=operator.getUserId())
			throw new OperatorNotAssociatedException("Operator :"+operator.getEmail()+" is not associated with Bus :"+bus.getBusId());
		bus.setAvailableSeats(bus.getAvailableSeats()+booking.getSeat().size());
		
		busRepo.save(bus);
		for(Seat i:booking.getSeat()) {
			seatService.cancelSeat(model.map(i, SeatDTO.class));
		}
		
		
		
		if(booking.getBookingStatus().equals("Payment Pending"))
			booking.setBookingStatus("Cancelled");
		else {
			booking.setBookingStatus("Refund Pending");
		}
		booking =bookingRepo.save(booking);
		savedBookingDTO=model.map(booking,BookingDTO.class);
		return savedBookingDTO;
	}

	@Override
	public List<BookingDTO> allBookings() throws BookingNotFoundException {
		List<BookingDTO> bookingList=null;
		List<Booking> bookings=bookingRepo.findAll();
		if(bookings.isEmpty()) 
			throw new BookingNotFoundException("No Booking Available");
		bookingList=bookings.stream()
				.map(booking->model.map(booking, BookingDTO.class))
				.collect(Collectors.toList());
		
		return bookingList;
	}
	
	
	

}
