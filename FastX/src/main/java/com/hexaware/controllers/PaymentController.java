package com.hexaware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.PaymentDTO;
import com.hexaware.model.Booking;
import com.hexaware.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/process")
	public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO){
		PaymentDTO processedPayment=paymentService.processPayment(paymentDTO);
		return new ResponseEntity<>(processedPayment,HttpStatus.CREATED);
	}
	
	@GetMapping("/paymentid/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable int paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }
	
	@PutMapping("/refund/{paymentId}/{bookingId}")
	public ResponseEntity<Booking> refundPayment(@PathVariable int paymentId, @PathVariable int bookingId) {

	    Booking updatedBooking = paymentService.updateRefundStatus(paymentId, bookingId);

	    if (updatedBooking != null) {
	        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


	
	

}