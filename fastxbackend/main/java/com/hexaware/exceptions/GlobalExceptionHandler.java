package com.hexaware.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.spel.ast.OperatorNot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.constraints.Email;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> bookingNotFound(BookingNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(),e.getMessage(),"Booking Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusAlreadyExistException.class)
    public ResponseEntity<?> busAlreadyExists(BusAlreadyExistException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Bus Already Exists", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusNotFoundException.class)
    public ResponseEntity<?> busNotFound(BusNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Bus Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperatorNotAssociatedException.class)
    public ResponseEntity<?> operatorNotAssociated(OperatorNotAssociatedException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Operator Not Associated", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> paymentNotFound(PaymentNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Payment Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RouteAlreadyExistsException.class)
    public ResponseEntity<?> routeAlreadyExists(RouteAlreadyExistsException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Route Already Exists", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<?> routeNotFound(RouteNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Route Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<?> seatNotFound(SeatNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Seat Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(UserNotFoundException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "User Not Found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingStatusNotMatchException.class)
    public ResponseEntity<?> bookingStatusNotMatch(BookingStatusNotMatchException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Booking Status are Not Matched", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExists(EmailAlreadyExistsException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Email Already Exists", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentAssociationException.class)
    public ResponseEntity<?> paymentAssociationException(PaymentAssociationException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Payment is not assioted to Booking", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<?> phoneAlreadyExists(PhoneAlreadyExistsException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Phone Number Already Exists", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<?> seatNotAvailable(SeatNotAvailableException e) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Seat Not Available", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException e){
    	
    	Map<String, String> errors = new HashMap<>();
        
        List<ObjectError> errList = e.getBindingResult().getAllErrors();
        errList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

    	
    	ErrorDetails error = new ErrorDetails(LocalDateTime.now(),errors,"Validation Failed",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    
}