package com.hexaware.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.model.Bus;
import com.hexaware.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
	
	@Query("select s from Seat s where s.bus=:bus and s.seatNumber=:seatNo")
	Seat findByBusAndSeatNo(@Param("bus") Bus bus, @Param("seatNo") String seatNo);
	
	List<Seat> findByBus(Bus bus);

}
