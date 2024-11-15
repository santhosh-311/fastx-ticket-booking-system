package com.hexaware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository <Payment,Integer>{

	Payment save(Payment payment);
	

}