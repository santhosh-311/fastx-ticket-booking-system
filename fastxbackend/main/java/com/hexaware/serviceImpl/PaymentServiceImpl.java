package com.hexaware.serviceImpl;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BookingStatusNotMatchException;
import com.hexaware.exceptions.PaymentAssociationException;
import com.hexaware.exceptions.PaymentNotFoundException;
import com.hexaware.model.Booking;
import com.hexaware.model.Payment;
import com.hexaware.model.Users;
import com.hexaware.repositories.BookingRepository;
import com.hexaware.repositories.PaymentRepository;
import com.hexaware.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
    private PaymentRepository paymentRepo;
	
	@Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ModelMapper model;

	@Override
	public PaymentDTO processPayment(Double amountPaid, Integer bookingId) throws BookingNotFoundException, BookingStatusNotMatchException {
		Payment payment = new Payment();
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking :"+bookingId+" Not Found"));
		if(!booking.getBookingStatus().equalsIgnoreCase("payment pending"))
			throw new BookingStatusNotMatchException("Booking :"+booking.getBookingId()+" has status :"+booking.getBookingStatus());
		
		Users user = booking.getUser();

		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentStatus("successfull");
		payment.setAmountPaid(amountPaid);
		payment.setBooking(booking);
		payment.setUser(user);
		payment=paymentRepo.save(payment);
		user.getPayment().add(payment);
		booking.setPayment(payment);
		booking.setBookingStatus("confirmed");
		bookingRepo.save(booking);
		
		return model.map(payment, PaymentDTO.class);
	}

	@Override
	public PaymentDTO getPaymentById(int paymentId) throws PaymentNotFoundException {
		Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment :"+paymentId+" not found"));
        return model.map(payment, PaymentDTO.class);
	}

	@Override
	public BookingDTO updateRefundStatus(int paymentId, int bookingId) throws PaymentNotFoundException, BookingNotFoundException, PaymentAssociationException {
		Payment payment=paymentRepo.findById(paymentId).orElseThrow(()-> new PaymentNotFoundException("Payment Not Found"));
		
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking Not Found"));
		if(payment.getBooking().getBookingId()!=booking.getBookingId()) 
			throw new PaymentAssociationException("Payment is not associated to booking");
		double refundamt=payment.getAmountPaid()*0.7;
		payment.setRefundStatus("Successfull");
		payment.setRefundAmount(refundamt);
		
		booking.setBookingStatus("Refunded");
		
		paymentRepo.save(payment);
        bookingRepo.save(booking);
        
        return model.map(booking, BookingDTO.class);
	}
}
