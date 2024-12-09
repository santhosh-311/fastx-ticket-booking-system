package com.hexaware.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Users;


@Repository
public interface UserRepository  extends JpaRepository<Users,Integer>{

//	Optional<User> findByName(String name);

	Users findByEmail(String email);

	Users findByNumber(String number);
	
}