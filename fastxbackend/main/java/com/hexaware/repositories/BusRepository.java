package com.hexaware.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Bus;
import com.hexaware.model.Route;
import com.hexaware.model.Users;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
	
	@Query("select b from Bus b where b.busNumber=:number and b.date =:date")
	public Bus busAddCheck(@Param("number") String number, @Param("date") LocalDate date);
	
	@Query("select b from Bus b where b.route=:route and b.date=:date and b.busType=:bustype")
	public List<Bus> searchBus(@Param("route") Route route, @Param("date") LocalDate date, @Param("bustype") String bustype);
	
	@Query("select b from Bus b where b.user=:user and b.route=:route and b.date=:date")
	public List<Bus> searchBusByOperator(@Param("user") Users user, @Param("route") Route route, @Param("date") LocalDate date );
	
	public List<Bus> findByUser(Users user);
}
