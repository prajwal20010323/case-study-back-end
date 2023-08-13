package com.prajwal.onlineRailwayBooking.controller;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prajwal.onlineRailwayBooking.exception.TainDetailsException;
import com.prajwal.onlineRailwayBooking.model.BookingDeatils;
import com.prajwal.onlineRailwayBooking.model.TrainDetails;
import com.prajwal.onlineRailwayBooking.model.TrainFare;
import com.prajwal.onlineRailwayBooking.service.TrainDetailsService;

@RestController
@CrossOrigin(origins = "*")
public class TrainDetailsController 
{
	private static Logger log = Logger.getLogger(TrainDetailsController.class);
	
	@Autowired
	private TrainDetailsService trainDetailsService;

	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - to add trains deatils
	 */
	@PostMapping("/addTrainDetails")
	public TrainDetails addTrain(@RequestBody TrainDetails trainDetails)  {
		log.info("Train details added successfully!");
		try {

			return trainDetailsService.insertTrainDetails(trainDetails);
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - View all trains deatils
	 */

	@GetMapping("/getAllTrainDetails")
	public List<TrainDetails> getTrainDetails() {
		log.info("View All Trains details !");
		try {
		return trainDetailsService.getTrainDetails();
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}

	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - updaet train availability calling in the booking micro service
	 */
	@PutMapping("/update/{trainNumber}/{trainClass}/{bookingStatus}")
	public String update(@PathVariable int trainNumber,@PathVariable String trainClass,@PathVariable String bookingStatus) {
		 if(trainDetailsService.updateTrainDetails(trainNumber,trainClass,bookingStatus)!=null)
				{
			 log.info("updation done!");
					return "updation Done!";
				}
		else {
			return "not update";
		}
	}
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - add train fare from source to destination
	 */
	@PostMapping("/addTrainFare")
	public TrainFare addTrainFare(@RequestBody TrainFare trainFare) {
		log.info("Train fare added successfully!");
		try {
		return trainDetailsService.addTrainFare(trainFare);
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - view fare of all the trains 
	 */

	@GetMapping("/getAllTrainFare")
	public List<TrainFare> getAllTrainFare() {
		log.info("getting all train fare!");
		try {
		return trainDetailsService.getAllTrainFare();
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}
	
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - view train fare by train number
	 */
	@GetMapping("/getTrainFare/{trainNumber}")
	public TrainFare getTrainFare(@PathVariable int trainNumber) {
		log.info("getting train fare by train number!");
		try {
			return trainDetailsService.getTrainFareByTrainNumber(trainNumber);
			
		}catch(Exception e){
			throw new TainDetailsException("invalid train number"+e.getMessage());
		}
	}

	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - search train from source to destination along with date
	 */
	@GetMapping("/search/{from}/{to}/{date}")
	public List<TrainFare> getTrain(@PathVariable String from, @PathVariable String to, @PathVariable String date) {
		log.info("search train from source to destination!");
		try {
		return trainDetailsService.searchTrain(from, to, date);
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}
	
	String date ="2023-07-01";
	/**
	 * Contains method to greet users by their name and location.
	 *
	 * @author Prajwal Sherkar 
	 * desciption - booking train details
	 */
	@GetMapping("/{trainNumber}/{from}/{to}")
	public BookingDeatils getBookingDetails(@PathVariable int trainNumber ,@PathVariable String from,@PathVariable String to)
	{
		try {
			TrainDetails trainDetailsObj = trainDetailsService.getTrainByTrainNumberAndDate(trainNumber, date);
			TrainFare trainFareObj =  trainDetailsService.getTrainFareByTrainNumberAndStationFromStationTo(trainNumber,from ,to);
			
			BookingDeatils bookingDeatilsObj = new BookingDeatils();
			
			bookingDeatilsObj.setTrainNumber(trainDetailsObj.getTrainNumber());
			bookingDeatilsObj.setTrainName(trainDetailsObj.getTrainName());
			bookingDeatilsObj.setStationFrom(trainDetailsObj.getStartStation());
			bookingDeatilsObj.setStationTo(trainDetailsObj.getEndStation());
			bookingDeatilsObj.setDate(trainDetailsObj.getDate());
			bookingDeatilsObj.setArrivalTime(trainFareObj.getArrivalTime());
			bookingDeatilsObj.setDepartureTime(trainDetailsObj.getDepartureStartStationTime());
			bookingDeatilsObj.setDestinationArrivalTime(trainDetailsObj.getArrivalEndStationTime());
			bookingDeatilsObj.setACAvailableSeats(trainDetailsObj.getACAvailableSeats());
			bookingDeatilsObj.setSLAvailableSeats(trainDetailsObj.getSLAvailableSeats());
			bookingDeatilsObj.setACWaiting(trainDetailsObj.getACWaiting());
			bookingDeatilsObj.setSLWaiting(trainDetailsObj.getSLWaiting());
			bookingDeatilsObj.setACFare(trainFareObj.getACFare());
			bookingDeatilsObj.setSLFare(trainFareObj.getSLFare());
			
			return bookingDeatilsObj;
		}catch(Exception e) {
			throw new TainDetailsException(e.getMessage());
		}
	}
	
	
}
