package com.hexaware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>{

	@Query("select r from Route r where r.routeFrom=:from and r.routeTo=:to")
	public Route findRoute(@Param("from") String routeFrom, @Param("to") String routeTo);

}
