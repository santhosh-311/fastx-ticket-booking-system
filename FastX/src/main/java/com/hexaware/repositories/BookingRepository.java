package com.hexaware.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
	
	public List<Booking> findByUser(Integer userId);
	
	public List<Booking> findByBus(Integer busId);
}
