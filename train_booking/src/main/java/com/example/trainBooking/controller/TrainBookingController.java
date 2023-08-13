package com.example.trainBooking.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.trainBooking.exception.TainBookingException;
import com.example.trainBooking.model.Book;
import com.example.trainBooking.model.BookingDetails;
import com.example.trainBooking.model.DemoTrainFare;
import com.example.trainBooking.model.UserDetails;
import com.example.trainBooking.service.TrainBookingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/trainBooking")
public class TrainBookingController 
{
	private static Logger log = Logger.getLogger(TrainBookingController.class);
	
	@Autowired
	private TrainBookingService trainBookingService;
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - to add booking deatils
	 * @throws TainBookingException 
	 */
	@PostMapping("/addBookingDetails")
	public BookingDetails addBookingDetails(@RequestBody BookingDetails bookingDetails)
	{
		log.info("Train details added successfully!");
		try {
		return trainBookingService.addBookingDetails(bookingDetails);
		}catch(Exception e) {
			throw new TainBookingException(e.getMessage());
		}
	}
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - viewing all booking deatils
	 * @throws TainBookingException 
	 */
	@GetMapping("/allBookingDetails")
	public List<BookingDetails> getAllBookingDetails()
	{
		log.info("All booking details !");
		try {
		return trainBookingService.getAllBookingDetails();
		}catch(Exception e) {
			throw new TainBookingException(e.getMessage());
		}
	}
	
	@PostMapping("/addUser")
	public UserDetails addUserDetails(@RequestBody UserDetails userDetails)
	{
		return trainBookingService.addUserDetails(userDetails);
	}
	
	@GetMapping("/getUsers")
	public List<UserDetails> getUser()
	{
		return trainBookingService.getUserDetails();
	}
	
	@DeleteMapping("/cancelBookingTicket/{PNRNumber}")
	public String cancelTrainBooking(@PathVariable String PNRNumber)
	{
		return trainBookingService.deleteByPNR(PNRNumber);
	}

	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - ticket booking
	 * @throws TainBookingException 
	 */
	
	@PostMapping("/ticketBooking")
	public BookingDetails addTicketBooking(@RequestBody Book book )
	{
		try {
			int trainNumber = book.getTrainNumber();
			String from = book.getStationFrom();
			String to = book.getStationTo();
			String trainClass = book.getTrainClass();
			String name= book.getName();
			String email= book.getEmail();
			int age = book.getAge();
			String gender=book.getGender();
			String success_payment_id = book.getRazorpay_payment_id();
			System.out.println(book);
			String sf1="http://localhost:8085/"+book.getTrainNumber()+"/"+book.getStationFrom()+"/"+book.getStationTo();
			
			
			ResponseEntity<DemoTrainFare> responseEntity =
					new RestTemplate().getForEntity(sf1,
							DemoTrainFare.class);
			
			DemoTrainFare object1 = responseEntity.getBody();
			System.out.println(object1.getDepartureTime());
			return trainBookingService.addTicketBooking(responseEntity,trainClass,name,age,gender,email,success_payment_id);
		}catch(Exception e) {
			throw new TainBookingException(e.getMessage());
		}
		
	}
	
	@GetMapping("/getTicketHistory/{email}")
	public List<BookingDetails> getTicketHistory(@PathVariable String email)
	{
		
		return trainBookingService.getTicketHistory(email);
	}
}
