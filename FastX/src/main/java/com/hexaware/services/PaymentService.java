package com.hexaware.services;

import org.springframework.stereotype.Service;

import com.hexaware.dto.PaymentDTO;
import com.hexaware.model.Booking;

@Service
public interface PaymentService {
	PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(int paymentId);
    Booking updateRefundStatus(int paymentId, int bookingid);

}