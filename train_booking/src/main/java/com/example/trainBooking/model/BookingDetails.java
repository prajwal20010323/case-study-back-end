package com.example.trainBooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking_details")
public class BookingDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
//	----	
	private int trainNumber;
	private String trainName;
	private String arrivalTime;
	private String departureTime;
	private String destinationArrivalTime;
	private String stationFrom;
	private String stationTo;
	private String date;
	private int trainFare;
	private String trainClass;
//	----------
	private String bookingStatus;
	private String PNRNumber;

	private String userName;
	private int age;
	private String gender;
	private String email;
	private String razorpay_payment_id;
	
	
	
}
