package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.SeatDTO;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.SeatNotAvailableException;
import com.hexaware.exceptions.SeatNotFoundException;
import com.hexaware.model.Seat;

@Service
public interface SeatService {
	
	//user
	Seat bookSeat(Integer busId, String seatNo) throws BusNotFoundException, SeatNotFoundException, SeatNotAvailableException;
	String cancelSeat(SeatDTO seatDTO) throws SeatNotFoundException;
	
	//operator
//	SeatDTO addSeat(SeatDTO seatDTO,Integer busId) throws BusNotFoundException, SeatNotFoundException;
//	String removeSeat(Integer busId, String seatNo) throws SeatNotFoundException, BusNotFoundException;
	List<SeatDTO> addMultlipleSeats(String seatCount, Integer busId) throws BusNotFoundException, SeatNotFoundException;
	
	//common
	List<SeatDTO> getSeats(Integer busId) throws BusNotFoundException, SeatNotFoundException;
	String removeMultipleSeats(String seatNo, Integer busId) throws BusNotFoundException, SeatNotFoundException;
	
	String blockSeats(Integer busId,String seatNumbers) throws BusNotFoundException, SeatNotFoundException;
	String unblockSeats(Integer busId,String seatNumbers) throws BusNotFoundException, SeatNotFoundException;

}
