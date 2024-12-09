package com.hexaware.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.BusDTO;
import com.hexaware.exceptions.BusAlreadyExistException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.RouteNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;

@Service
public interface BusService {
	
	//operator
	BusDTO addBus(BusDTO busDTO,Integer routeId,String email) throws BusAlreadyExistException, UserNotFoundException, RouteNotFoundException;
	BusDTO updateBusDetails(BusDTO busDTO,Integer routeId) throws BusNotFoundException, RouteNotFoundException;
//	BusDTO updateBusRoutes(BusDTO busDTO, String routeFrom, String routeTo) throws BusNotFoundException, RouteNotFoundException;
	String deleteBus(Integer busId) throws BusNotFoundException;
	List<BusDTO> searchBus(String email, String routeFrom, String routeTo, LocalDate date) throws UserNotFoundException, RouteNotFoundException, BusNotFoundException;
	
	List<BusDTO> getBus(String email) throws UserNotFoundException;

	
	//user //admin
	List<BusDTO> searchBus(String routeFrom, String routeTo, LocalDate date,String bustype) throws RouteNotFoundException, BusNotFoundException;

	//user//admin//operator
	BusDTO getBus(Integer busId) throws BusNotFoundException;
}
