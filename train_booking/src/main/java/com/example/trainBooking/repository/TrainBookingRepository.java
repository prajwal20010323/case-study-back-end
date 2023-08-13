package com.example.trainBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trainBooking.model.BookingDetails;

@Repository
public interface TrainBookingRepository extends JpaRepository<BookingDetails, Integer>{


	int deleteBookingDetailsByPNRNumber(String PNRNumber);

	List<BookingDetails> findByEmail(String email);

}
