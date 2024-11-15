package com.hexaware.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.dto.BusDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.OperatorNotAssociatedException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.dto.SeatDTO;
import com.hexaware.model.Booking;
import com.hexaware.model.Bus;
import com.hexaware.model.Seat;
import com.hexaware.model.User;
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
	public BookingDTO createBoooking(BookingDTO bookingDTO, Integer userId, Integer busId) throws UserNotFoundException, BusNotFoundException, SeatNotFoundException {
		//checking for user,and bus
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User :"+userId+" Not Found"));
		Bus bus= busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		
		//creating a booking object
		Booking booking = model.map(bookingDTO, Booking.class);
		
		List<String> seatNo= new ArrayList<>(Arrays.asList(booking.getSeatInfo().split(",")));           
		
		//setting relations
		booking.setUser(user);
		booking.setBus(bus);
		bus.getBooking().add(booking);
		user.getBooking().add(booking);
		
		//updates seats
		List<Seat> seats= new ArrayList<>();
		for(String i:seatNo) {
			SeatDTO seatDTO=seatService.bookSeat(busId,i);
			Seat seat=model.map(seatDTO, Seat.class);
			seat.setBooking(booking);
			seat=seatRepo.saveAndFlush(seat);
			seats.add(seat);
		}
		
		//setting seat relations
		booking.setSeat(seats);
		
		//saving to the repository and taking its address
		booking=bookingRepo.save(booking);
		
		//converting the objects to DTO type
		UserDTO userDTO=model.map(user, UserDTO.class);
		BusDTO busDTO=model.map(bus, BusDTO.class);
		BookingDTO savedBookingDTO= model.map(booking, BookingDTO.class);
		
		//setting the relations for DTO
		savedBookingDTO.setUser(userDTO);
		savedBookingDTO.setBus(busDTO);
		
		return savedBookingDTO;
	}
	
	@Override
	public BookingDTO cancelBooking(Integer bookingId) throws BookingNotFoundException, SeatNotFoundException {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		booking.setBookingStatus("pending");
		for(Seat i:booking.getSeat()) {
			seatService.cancelSeat(model.map(i, SeatDTO.class));
		}
		booking.setSeat(null);
		booking = bookingRepo.saveAndFlush(booking);
		
		return model.map(booking, BookingDTO.class);
	}
	
	@Override
	public List<BookingDTO> myBookings(Integer userId) throws UserNotFoundException, BookingNotFoundException{
		List<BookingDTO> bookingList=null;
		
		User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User :"+userId+" Not Found"));
		List<Booking> bookings = bookingRepo.findByUser(user.getUserId());
		if(bookings.isEmpty())
			throw new BookingNotFoundException("Bookings with userId :"+user.getUserId()+" Not Found");
		bookingList = bookings.stream()
                .map(booking -> model.map(booking, BookingDTO.class))
                .collect(Collectors.toList());			
	
		return bookingList;
	}

	@Override
	public BookingDTO getBooking(Integer bookingId) throws BookingNotFoundException {
		
		Booking booking =bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		return model.map(booking, BookingDTO.class);
	}

	@Override
	public List<BookingDTO> viewBookings(Integer operatorId,Integer busId) throws BusNotFoundException, UserNotFoundException, OperatorNotAssociatedException, BookingNotFoundException {
		List<BookingDTO> bookingList=null;
		
		User operator = userRepo.findById(operatorId).orElseThrow(()-> new UserNotFoundException("Operator with userId :"+operatorId+" Not Found"));
		Bus bus=busRepo.findById(busId).orElseThrow(()->new BusNotFoundException("Bus :"+busId+" Not Found"));
		if(bus.getUser().getUserId()!=operatorId)
			throw new OperatorNotAssociatedException("Operator :"+operator.getUserId()+" is not associated with Bus :"+bus.getBusId());
		
		List<Booking> bookings = bookingRepo.findByBus(bus.getBusId());
		if(bookings.isEmpty())
			throw new BookingNotFoundException("Bookings with Bus: "+busId+"Not Found");
		bookingList = bookings.stream()
                .map(booking -> model.map(booking, BookingDTO.class))
                .collect(Collectors.toList());			
		
		return bookingList;
	}

	@Override
	public BookingDTO bookingCancellation(Integer operatorId,Integer bookingId) throws BookingNotFoundException, UserNotFoundException, OperatorNotAssociatedException {
		BookingDTO savedBookingDTO=null;
		User operator = userRepo.findById(operatorId).orElseThrow(()-> new UserNotFoundException("Operator with userId :"+operatorId+" Not Found"));
		Booking booking=bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		Bus bus=booking.getBus();
		
		if(bus.getUser().getUserId()!=operatorId)
			throw new OperatorNotAssociatedException("Operator :"+operator.getUserId()+" is not associated with Bus :"+bus.getBusId());
		
		booking.setBookingStatus("refund intiated");
		booking =bookingRepo.saveAndFlush(booking);
		savedBookingDTO=model.map(booking,BookingDTO.class);
		return savedBookingDTO;
	}

	@Override
	public List<BookingDTO> allBookings() throws BookingNotFoundException {
		List<BookingDTO> bookingDTOList=null;
		List<Booking> bookingList=bookingRepo.findAll();
		if(bookingList.isEmpty()) 
			throw new BookingNotFoundException("No Booking Available");
		bookingDTOList=bookingList.stream()
				.map(booking->model.map(booking, BookingDTO.class))
				.collect(Collectors.toList());
		
		return bookingDTOList;
	}
	
	
	

}
