package com.hexaware.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.BusDTO;
import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.BusAlreadyExistException;
import com.hexaware.exceptions.BusNotFoundException;
import com.hexaware.exceptions.RouteNotFoundException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.model.Bus;
import com.hexaware.model.Route;
import com.hexaware.model.Users;
import com.hexaware.repositories.BusRepository;
import com.hexaware.repositories.RouteRepository;
import com.hexaware.repositories.UserRepository;
import com.hexaware.services.BusService;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Override
	public BusDTO addBus(BusDTO busDTO, Integer routeId,String email) throws BusAlreadyExistException, UserNotFoundException, RouteNotFoundException {
		Bus bus = model.map(busDTO, Bus.class);
		Users operator = userRepo.findByEmail(email);
		if(operator==null)
			throw new UserNotFoundException("Operator Not Found "+email);
		Bus checkbus= busRepo.busAddCheck(bus.getBusNumber(),bus.getDate());
		Route route = routeRepo.findById(routeId).orElseThrow(()-> new RouteNotFoundException("Route :"+routeId+"Not Found"));
		if(checkbus!=null)
			throw new BusAlreadyExistException("Bus :"+bus.getBusId()+" already exists");
		
		
		bus.setUser(operator);
		operator.getBus().add(bus);
		userRepo.save(operator);
		
		bus.setRoute(route);
		route.getBus().add(bus);
		routeRepo.save(route);

		bus=busRepo.save(bus);

		
		
		BusDTO savedBus=model.map(bus,BusDTO.class);
//		savedBus.setUser(model.map(operator, UserDTO.class));
		
		return savedBus;
	}

	@Override
	public BusDTO updateBusDetails(BusDTO busDTO,Integer routeId) throws BusNotFoundException,RouteNotFoundException {
		
		Bus bus = busRepo.findById(busDTO.getBusId()).orElseThrow(()-> new BusNotFoundException("Bus :"+busDTO.getBusId()+" Not Found"));
		Route route = routeRepo.findById(routeId).orElseThrow(()-> new RouteNotFoundException("Route not found"));
		bus.setAmenities(busDTO.getAmenities());
		bus.setAvailableSeats(busDTO.getAvailableSeats());
		bus.setBusName(busDTO.getBusName());
		bus.setBusNumber(busDTO.getBusNumber());
		bus.setTotalSeats(busDTO.getTotalSeats());
		bus.setBusType(busDTO.getBusType());
		bus.setAmenities(busDTO.getAmenities());
		bus.setDate(busDTO.getDate());
		bus.setArrival(busDTO.getArrival());
		bus.setDeparture(busDTO.getDeparture());
		bus.setPricePerSeat(busDTO.getPricePerSeat());
		
		bus.setRoute(route);
		route.getBus().add(bus);
		
		bus =busRepo.save(bus);
		routeRepo.save(route);

		return model.map(bus, BusDTO.class);
	}

//	@Override
//	public BusDTO updateBusRoutes(BusDTO busDTO, String routeFrom, String routeTo) throws BusNotFoundException, RouteNotFoundException {
//		Bus bus = busRepo.findById(busDTO.getBusId()).orElseThrow(()-> new BusNotFoundException("Bus :"+busDTO.getBusId()+" Not Found"));
//		Route route = routeRepo.findRoute(routeFrom, routeTo);
//		if(route==null)
//			throw new RouteNotFoundException("Route not found");
//		
//		bus.setRoute(route);
//		route.getBus().add(bus);
//		
//		bus = busRepo.save(bus);
//		routeRepo.save(route);
//		
//		return model.map(bus, BusDTO.class);
//	}

	@Override
	public String deleteBus(Integer busId) throws BusNotFoundException {
		Bus bus=busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		if(LocalDate.now().compareTo(bus.getDate())>0) 
			busRepo.delete(bus);
		else if(bus.getTotalSeats()==bus.getAvailableSeats())
			busRepo.delete(bus);
		else
			return "Bus schedule is not complete";
		return "Bus Deleted";
	}

	@Override
	public List<BusDTO> searchBus(String email, String routeFrom, String routeTo, LocalDate date) throws UserNotFoundException, RouteNotFoundException, BusNotFoundException {
		Route route = routeRepo.findRoute(routeFrom,routeTo);
		if(route==null)
			throw new RouteNotFoundException("Route Not Found");
		Users operator = userRepo.findByEmail(email);
		if(operator==null)
			throw new UserNotFoundException("Operator Not Found "+email);
		List<Bus> busList = busRepo.searchBusByOperator(operator,route,date);
		if(busList.isEmpty())
			throw new BusNotFoundException("Buses Not Found");
		return busList.stream().map(bus -> model.map(bus, BusDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<BusDTO> searchBus(String routeFrom, String routeTo, LocalDate date, String bustype) throws RouteNotFoundException, BusNotFoundException {
		
		Route route = routeRepo.findRoute(routeFrom,routeTo);
		if(route==null)
			throw new RouteNotFoundException("Route Not Found");
		List<Bus> busList = busRepo.searchBus(route,date,bustype);
		if(busList.isEmpty())
			throw new BusNotFoundException("Buses Not Found");
		return busList.stream().map(bus -> model.map(bus, BusDTO.class)).collect(Collectors.toList());
	}

	@Override
	public BusDTO getBus(Integer busId) throws BusNotFoundException {
		Bus bus = busRepo.findById(busId).orElseThrow(()-> new BusNotFoundException("Bus :"+busId+" Not Found"));
		return model.map(bus,BusDTO.class);		
	}

	@Override
	public List<BusDTO> getBus(String email) throws UserNotFoundException {
		Users operator = userRepo.findByEmail(email);
		if(operator==null)
			throw new UserNotFoundException("Operator Not Found "+email);
		List<Bus> busList =busRepo.findByUser(operator);
		return busList.stream().map(bus -> model.map(bus, BusDTO.class)).collect(Collectors.toList());
	}

}




























