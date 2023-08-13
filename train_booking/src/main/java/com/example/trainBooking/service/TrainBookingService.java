package com.example.trainBooking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.trainBooking.model.BookingDetails;
import com.example.trainBooking.model.DemoTrainFare;
import com.example.trainBooking.model.UserDetails;

@Service
public interface TrainBookingService {

	BookingDetails addBookingDetails(BookingDetails bookingDetails);

	List<BookingDetails> getAllBookingDetails();

	UserDetails addUserDetails(UserDetails userDetails);

	List<UserDetails> getUserDetails();


	BookingDetails addTicketBooking(ResponseEntity<DemoTrainFare> responseEntity, String trainClass, String name, int age, String gender,String email,String success_payment_id);

	String deleteByPNR(String PNRNumber);

	List<BookingDetails> getTicketHistory(String email);

}
	