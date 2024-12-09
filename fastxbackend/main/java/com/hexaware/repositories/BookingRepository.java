package com.hexaware.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Booking;
import com.hexaware.model.Bus;
import com.hexaware.model.Users;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
	
	public List<Booking> findByUser(Users user);
	
	public List<Booking> findByBus(Bus bus);
}
