package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.SeatNotFoundException;

@Service
public interface SeatService {
	
	//user
	SeatDTO bookSeat(Integer busId, String seatNo) throws BusNotFoundException, SeatNotFoundException;
	String cancelSeat(SeatDTO seatDTO) throws SeatNotFoundException;
	
	//operator
	SeatDTO addSeat(SeatDTO seatDTO,Integer busId) throws BusNotFoundException, SeatNotFoundException;
	String removeSeat(Integer seatId) throws SeatNotFoundException;
	
	//common
	List<SeatDTO> getSeats(Integer busId) throws BusNotFoundException, SeatNotFoundException;
	
}
