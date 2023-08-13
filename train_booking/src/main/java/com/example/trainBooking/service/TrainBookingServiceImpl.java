package com.example.trainBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.trainBooking.model.BookingDetails;
import com.example.trainBooking.model.DemoTrainFare;
import com.example.trainBooking.model.PNRGenerator;
import com.example.trainBooking.model.UserDetails;
import com.example.trainBooking.repository.TrainBookingRepository;
import com.example.trainBooking.repository.UserDetailsRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TrainBookingServiceImpl implements TrainBookingService
{
	@Autowired
	private TrainBookingRepository trainBookingRepository;
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) {
		return trainBookingRepository.save(bookingDetails);
	}

	@Override
	public List<BookingDetails> getAllBookingDetails() {
		return trainBookingRepository.findAll();
	}

	@Override
	public UserDetails addUserDetails(UserDetails userDetails) {
		return userDetailsRepo.save(userDetails);
	}

	@Override
	public List<UserDetails> getUserDetails() {
		return userDetailsRepo.findAll();
	}
	
	@Override
	public BookingDetails addTicketBooking(ResponseEntity<DemoTrainFare> responseEntity,String trainClass,String name,int age, String gender,String email,String success_payment_id) 
	{
		DemoTrainFare demoTrainFareObj = responseEntity.getBody();
		System.out.println(demoTrainFareObj);
		BookingDetails bookingDetails = new BookingDetails();
		

		bookingDetails.setTrainNumber(demoTrainFareObj.getTrainNumber());
		bookingDetails.setTrainName(demoTrainFareObj.getTrainName());
		bookingDetails.setArrivalTime(demoTrainFareObj.getArrivalTime());
		bookingDetails.setDepartureTime(demoTrainFareObj.getDepartureTime());
		bookingDetails.setDestinationArrivalTime(demoTrainFareObj.getDestinationArrivalTime());
		bookingDetails.setStationFrom(demoTrainFareObj.getStationFrom());
		bookingDetails.setStationTo(demoTrainFareObj.getStationTo());
		bookingDetails.setDate(demoTrainFareObj.getDate());
		bookingDetails.setStationTo(demoTrainFareObj.getStationTo());
		bookingDetails.setTrainClass(trainClass);
		bookingDetails.setEmail(email);
		
		Optional<UserDetails> usreDetailsObj = userDetailsRepo.findById(1);
		bookingDetails.setUserName(name);
		bookingDetails.setAge(age);
		bookingDetails.setGender(gender);
		bookingDetails.setRazorpay_payment_id(success_payment_id);
		System.out.println(success_payment_id);
		PNRGenerator pnr = new PNRGenerator();
		
		bookingDetails.setPNRNumber("PNR"+demoTrainFareObj.getTrainNumber() + pnr.generatePNR());
		
		if(trainClass.equals("SL"))
		{
			int trainNumber = responseEntity.getBody().getTrainNumber();
			int SLFare = responseEntity.getBody().getSLFare();
			int SLAvailableSeats =  responseEntity.getBody().getSLAvailableSeats();
			System.out.println(SLAvailableSeats);
			bookingDetails.setTrainFare(SLFare);
			String bookingStatus = null;
			
			
			if(SLAvailableSeats > 0)
			{
				bookingStatus = "Confirm";
				bookingDetails.setBookingStatus(bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				// availble seats update with -1
				String sf2=String.format("http://localhost:8085/update/%d/%s/%s",trainNumber,trainClass,bookingStatus);
				restTemplate.put(sf2, String.class);
				
				// add booking details
				 return trainBookingRepository.save(bookingDetails);
				 
			}
			else {
				bookingStatus = "Waiting";		
				bookingDetails.setBookingStatus(bookingStatus);
				// waiting seats update with +1
				String sf2=String.format("http://localhost:8085/update/%d/%s/%s",trainNumber,trainClass,bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(sf2, String.class);
				
				// add booking details
				 return trainBookingRepository.save(bookingDetails);
			}
			
		}
		
		else {
			int trainNumber = responseEntity.getBody().getTrainNumber();
			int ACFare = responseEntity.getBody().getACFare();
			int ACAvailableSeats = responseEntity.getBody().getACAvailableSeats();
			bookingDetails.setTrainFare(ACFare);
			String bookingStatus = null;
			
			if(ACAvailableSeats > 0)
			{
				bookingStatus = "Confirm";
				bookingDetails.setBookingStatus(bookingStatus);
				// availble seats update with -1
				String sf2=String.format("http://localhost:8085/update/%d/%s/%s",trainNumber,trainClass,bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(sf2, String.class);
				
				
				// add booking details
				 return trainBookingRepository.save(bookingDetails);
			}
			else {
				bookingStatus = "Waiting";
				bookingDetails.setBookingStatus(bookingStatus);
				// waiting seats update with +1
				String sf2=String.format("http://localhost:8085/update/%d/%s/%s",trainNumber,trainClass,bookingStatus);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put(sf2, String.class);
				
				// add booking details
				 return trainBookingRepository.save(bookingDetails);
			}
		}
		
	}

	@Override
	public String deleteByPNR(String PNRNumber) 
	{
		if(trainBookingRepository.deleteBookingDetailsByPNRNumber(PNRNumber)>0)
		{
			return "Booking Cancel!";
		}
		else
		{
			return "Not Cancel";
		}
	}

	
	public List<BookingDetails> getTicketHistory(String email)
	{
		return trainBookingRepository.findByEmail(email);
	}
	
}
