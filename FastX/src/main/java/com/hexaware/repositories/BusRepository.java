package com.hexaware.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Bus;
import com.hexaware.model.Route;
import com.hexaware.model.User;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
	
	@Query("select b from Bus where busNumber:= number and date :=date")
	public boolean busAddCheck(@Param("number") String number, @Param("date") LocalDate date);
	
	@Query("select b from Bus where route:=route and date:=date")
	public List<Bus> searchBus(@Param("route") Route route, @Param("date") LocalDate date);
	
	@Query("select b from Bus where user:=user and route:=route and date:=date")
	public List<Bus> searchBusByOperator(@Param("user") User user, @Param("route") Route route, @Param("date") LocalDate date );
}
