package com.hexaware.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.model.Bus;
import com.hexaware.model.Seat;
import com.hexaware.repositories.BusRepository;
import com.hexaware.repositories.SeatRepository;
import com.hexaware.services.SeatService;


@Service
public class SeatServiceImpl implements SeatService{
	
	@Autowired
	private SeatRepository seatRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private ModelMapper model;

	@Override
	public Seat bookSeat(Integer busId, String seatNo) throws BusNotFoundException, SeatNotFoundException, SeatNotAvailableException {
		SeatDTO seatDTO=null;
		
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus  Not Found"));
		Seat seat = seatRepo.findByBusAndSeatNo(bus,seatNo);
		if(seat==null)
			throw new SeatNotFoundException("Bus has no Seat: "+seatNo);
		if(!seat.isAvailable())
			throw new SeatNotAvailableException("Seat Not Available to book");
		seat.setAvailable(false);
		return seat;
	}

	@Override
	public String cancelSeat(SeatDTO seatDTO) throws SeatNotFoundException {
		Seat seat=seatRepo.findById(seatDTO.getSeatId()).orElseThrow(()-> new SeatNotFoundException("Seat :"+seatDTO.getSeatId()+" Not Found"));
		
		seat.setAvailable(true);
		seat.setBooking(null);
		seatRepo.save(seat);
			
		return "Seat is Cancelled";
	}

//	@Override
//	public SeatDTO addSeat(SeatDTO seatDTO, Integer busId) throws BusNotFoundException, SeatNotFoundException {
//		
//		Seat seat = model.map(seatDTO,Seat.class);
//		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
//		
//		if(seatRepo.findByBusAndSeatNo(bus, seat.getSeatNumber())!=null) 
//			throw new SeatNotFoundException("Bus :"+bus.getBusId()+" already has SeatNo: "+seat.getSeatNumber());
//		seat.setAvailable(true);
//		seat.setBus(bus);
//		seat=seatRepo.save(seat);
//		bus.getSeat().add(seat);
//		busRepo.save(bus);
//		SeatDTO savedSeat=model.map(seat, SeatDTO.class);
//		return savedSeat;
//	}
//
//	@Override
//	public String removeSeat(Integer busId, String seatNo) throws SeatNotFoundException, BusNotFoundException {
//		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
//		Seat seat=seatRepo.findByBusAndSeatNo(bus, seatNo);
//		if(seat==null)
//			throw new SeatNotFoundException("Bus :"+busId+" has no SeatNo :"+seatNo);
//		seatRepo.delete(seat);;
//		return "Seat deleted";
//	}

	@Override
	public List<SeatDTO> getSeats(Integer busId) throws BusNotFoundException, SeatNotFoundException {
		Bus bus=busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+"Not Found"));
		List<SeatDTO> seatList = null;

		List<Seat> seats= seatRepo.findByBus(bus);
		if(seats.isEmpty()) 
			throw new SeatNotFoundException("No Seat associated to Bus :"+bus.getBusId());
		seatList = seats.stream()
				.map(seat -> model.map(seat, SeatDTO.class))
				.collect(Collectors.toList());
		return seatList;
	}

	@Override
	public List<SeatDTO> addMultlipleSeats(String seatCount, Integer busId) throws BusNotFoundException, SeatNotFoundException {
		
		List<Seat> seatList = new ArrayList<>();
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		Seat seat=null;
		for(int i=0;i<Integer.parseInt(seatCount);i++) {
			String s=String.valueOf(i);
			seat = seatRepo.findByBusAndSeatNo(bus,s);
			if(seat==null) {
				seat = new Seat();
				seat.setSeatNumber(s);
				seat.setAvailable(true);
				seat.setBus(bus);
				bus.getSeat().add(seat);
				seat =seatRepo.save(seat);
				seatList.add(seat);
			}
		}
		bus.setAvailableSeats(bus.getAvailableSeats()+seatList.size());
		bus.setTotalSeats(bus.getTotalSeats()+seatList.size());
		busRepo.save(bus);
		
		return seatList.stream().map(s -> model.map(s, SeatDTO.class)).toList();
	}
	
	@Override
	public String removeMultipleSeats(String seatNo, Integer busId) throws BusNotFoundException, SeatNotFoundException{
		List<String> seatList = new ArrayList<>();
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		Seat seat=null;
		for(String s:seatNo.split(",")) {
			seat = seatRepo.findByBusAndSeatNo(bus,s);
			if(seat!=null) {
				seatList.add(s);
				seatRepo.delete(seat);
			}
		}
		bus.setAvailableSeats(bus.getAvailableSeats()-seatList.size());
		bus.setTotalSeats(bus.getTotalSeats()-seatList.size());
		return String.format("List of Seats Removed: %s",seatList);
	}

	@Override
	public String blockSeats(Integer busId, String seatNumbers) throws BusNotFoundException, SeatNotFoundException {
		List<String> seatList = new ArrayList<>(Arrays.asList(seatNumbers.split(",")));
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		Seat seat=null;
		for(String s:seatList) {
			seat=seatRepo.findByBusAndSeatNo(bus, s);
			if(seat!=null) {
				seat.setAvailable(false);
				seatRepo.save(seat);
			}
		}
		return "Seats Blocked";
	}
	@Override
	public String unblockSeats(Integer busId, String seatNumbers) throws BusNotFoundException, SeatNotFoundException {
		List<String> seatList = new ArrayList<>(Arrays.asList(seatNumbers.split(",")));
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		Seat seat=null;
		for(String s:seatList) {
			seat=seatRepo.findByBusAndSeatNo(bus, s);
			if(seat!=null && seat.getBooking()==null && !seat.isAvailable()) {
				seat.setAvailable(true);
				seatRepo.save(seat);
			}
		}
		return "Seats Unblocked";
	}
	

}
