package com.prajwal.onlineRailwayBooking.service;

import java.util.List;

import com.prajwal.onlineRailwayBooking.model.TrainDetails;
import com.prajwal.onlineRailwayBooking.model.TrainFare;

	
public interface TrainDetailsService 
{

	TrainDetails insertTrainDetails(TrainDetails trainDetails);

//	TrainDetails getTrainDetails();

	List<TrainDetails> getTrainDetails();

	public TrainFare addTrainFare(TrainFare trainFare);

	public List<TrainFare> getAllTrainFare();

	public List<TrainFare> searchTrain(String from, String to, String date);

	public TrainDetails updateTrainDetails(TrainDetails trainDetails);

	TrainDetails getTrainByTrainNumberAndDate(int trainNumber, String date);

	TrainFare getTrainFareByTrainNumber(int trainNumber);


	TrainFare getTrainFareByTrainNumberAndStationFromStationTo(int trainNumber, String from, String to);


	TrainDetails updateTrainDetails(int trainNumber, String trainClass, String bookingStatus);


//	TrainFare getTrainFareByTrainNumber(String trainNumber);


//	TrainDetails updateTrainDetails(TrainDetails trainDetails);

}
