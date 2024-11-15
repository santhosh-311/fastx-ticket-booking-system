package com.hexaware.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.PaymentDTO;
import com.hexaware.model.Booking;
import com.hexaware.model.Payment;
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
    private ModelMapper modelMapper;

	@Override
	public PaymentDTO processPayment(PaymentDTO paymentDTO) {
		Payment payment=modelMapper.map(paymentDTO, Payment.class);
		Payment savepayment=paymentRepo.save(payment);
		return modelMapper.map(savepayment, PaymentDTO.class);
	}

	@Override
	public PaymentDTO getPaymentById(int paymentId) {
		Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        return modelMapper.map(payment, PaymentDTO.class);
    
	}

	@Override
	public Booking updateRefundStatus(int paymentId, int bookingid) {
		Payment payment=paymentRepo.findById(paymentId).orElse(null);
		if(payment!=null && payment.getBookingId()==bookingid) {
			double refundamt=payment.getAmountPaid()*0.7;
			payment.setRefundStatus("Updated");
			payment.setRefundAmount(refundamt);
			
			Booking booking=payment.getBooking();
			booking.setBookingStatus("Refunded");
			
			paymentRepo.save(payment);
            bookingRepo.save(booking);
            
            return booking;
		}
		return null;
	}
}