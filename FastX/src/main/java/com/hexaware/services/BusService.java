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
	BusDTO addBus(BusDTO busDTO,Integer operatorId) throws BusAlreadyExistException;
	BusDTO updateBusDetails(BusDTO busDTO) throws BusNotFoundException;
	BusDTO updateBusRoutes(BusDTO busDTO, String routeFrom, String routeTo) throws BusNotFoundException, RouteNotFoundException;
	String deleteBus(Integer busId) throws BusNotFoundException;
	List<BusDTO> searchBus(Integer operatorId, String routeFrom, String routeTo, LocalDate date) throws UserNotFoundException, RouteNotFoundException, BusNotFoundException;
	
	//user //admin
	List<BusDTO> searchBus(String routeFrom, String routeTo, LocalDate date) throws RouteNotFoundException, BusNotFoundException;

	//user//admin//operator
	BusDTO getBus(Integer busId) throws BusNotFoundException;
}
