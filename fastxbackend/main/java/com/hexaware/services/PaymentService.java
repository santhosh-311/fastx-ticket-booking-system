package com.hexaware.services;

import org.springframework.stereotype.Service;

import com.hexaware.dto.BookingDTO;
import com.hexaware.dto.PaymentDTO;
import com.hexaware.exceptions.BookingNotFoundException;
import com.hexaware.exceptions.BookingStatusNotMatchException;
import com.hexaware.exceptions.PaymentAssociationException;
import com.hexaware.exceptions.PaymentNotFoundException;
import com.hexaware.model.Booking;

@Service
public interface PaymentService {
    PaymentDTO getPaymentById(int paymentId) throws PaymentNotFoundException;
    BookingDTO updateRefundStatus(int paymentId, int bookingid) throws PaymentNotFoundException, BookingNotFoundException, PaymentAssociationException;
	PaymentDTO processPayment(Double amountPaid, Integer bookingId) throws BookingNotFoundException, BookingStatusNotMatchException;

}
