package com.hexaware.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
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
	public SeatDTO bookSeat(Integer busId, String seatNo) throws BusNotFoundException, SeatNotFoundException {
		SeatDTO seatDTO=null;
		
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		Seat seat = seatRepo.findByBusAndSeatNo(bus,seatNo);
		if(seat==null)
			throw new SeatNotFoundException("Bus :"+bus.getBusId()+" has no Seat: "+seatNo);
		seat.setAvailable(false);
		seat=seatRepo.save(seat);
		seatDTO=model.map(seat,SeatDTO.class);
		return seatDTO;
	}

	@Override
	public String cancelSeat(SeatDTO seatDTO) throws SeatNotFoundException {
		Seat seat=seatRepo.findById(seatDTO.getSeatId()).orElseThrow(()-> new SeatNotFoundException("Seat :"+seatDTO.getSeatId()+" Not Found"));
		
		seat.setAvailable(true);
		seat.setBooking(null);
		seatRepo.save(seat);
			
		return "Seat is Cancelled";
	}

	@Override
	public SeatDTO addSeat(SeatDTO seatDTO, Integer busId) throws BusNotFoundException, SeatNotFoundException {
		
		Seat seat = model.map(seatDTO,Seat.class);
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		
		if(seatRepo.findByBusAndSeatNo(bus, seat.getSeatNumber())==null) 
			throw new SeatNotFoundException("Bus :"+bus.getBusId()+" has no Seat: "+seat.getSeatNumber());

		seat.setBus(bus);
		seat=seatRepo.save(seat);
		bus.getSeat().add(seat);
		busRepo.save(bus);
		SeatDTO savedSeat=model.map(seat, SeatDTO.class);
		return savedSeat;
	}

	@Override
	public String removeSeat(Integer seatId) throws SeatNotFoundException {
		Seat seat=seatRepo.findById(seatId).orElseThrow(()-> new SeatNotFoundException("Seat :"+seatId+" Not Found"));
		seatRepo.delete(seat);;
		return "Seat deleted";
	}

	@Override
	public List<SeatDTO> getSeats(Integer busId) throws BusNotFoundException, SeatNotFoundException {
		Bus bus=busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+"Not Found"));
		List<SeatDTO> seatList = null;

		List<Seat> seats= seatRepo.findByBusId(bus.getBusId());
		if(seats.isEmpty()) 
			throw new SeatNotFoundException("No Seat associated to Bus :"+bus.getBusId());
		seatList = seats.stream()
				.map(seat -> model.map(seat, SeatDTO.class))
				.collect(Collectors.toList());
		return seatList;
	}
	

}
