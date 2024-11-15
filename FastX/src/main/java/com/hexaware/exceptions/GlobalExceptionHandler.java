package com.hexaware.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> customerNotFound(UserNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<?> customerNotFound(BusNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<?> customerNotFound(BookingNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<?> customerNotFound(RouteNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<?> customerNotFound(SeatNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<?> customerNotFound(PaymentNotFoundException e){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"location not found","User Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
}

