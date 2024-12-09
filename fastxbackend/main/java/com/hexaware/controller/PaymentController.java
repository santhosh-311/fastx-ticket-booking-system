package com.hexaware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.BookingDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BookingStatusNotMatchException;
import com.hexaware.exceptions.PaymentAssociationException;
import com.hexaware.exceptions.PaymentNotFoundException;
import com.hexaware.model.Booking;
import com.hexaware.services.PaymentService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/process/{bookingId}/{amountPaid}")
	public ResponseEntity<PaymentDTO> processPayment(@PathVariable Integer bookingId,@PathVariable Double amountPaid) throws BookingNotFoundException, BookingStatusNotMatchException{
		PaymentDTO processedPayment=paymentService.processPayment(amountPaid, bookingId);
		return new ResponseEntity<>(processedPayment,HttpStatus.CREATED);
	}
	
	@GetMapping("/getpayment/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable int paymentId) throws PaymentNotFoundException {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }
	
	@PutMapping("/refund/{paymentId}/{bookingId}")
	public ResponseEntity<BookingDTO> refundPayment(@PathVariable int paymentId, @PathVariable int bookingId) throws PaymentNotFoundException, BookingNotFoundException, PaymentAssociationException {

	    BookingDTO updatedBooking = paymentService.updateRefundStatus(paymentId, bookingId);
	    return new ResponseEntity<BookingDTO>(updatedBooking, HttpStatus.OK);
	}


	
	

}
