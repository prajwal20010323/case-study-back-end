package com.prajwal.onlineRailwayBooking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prajwal.onlineRailwayBooking.model.TrainDetails;
import com.prajwal.onlineRailwayBooking.model.TrainFare;

public interface RailwayDetailsRepo extends MongoRepository <TrainDetails , String>
{


	TrainFare insert(TrainFare trainFare);

	List<TrainDetails> findByDateAndTrainNumber(String date, int trainNumber);

	TrainDetails findByTrainNumberAndDate(int trainNumber, String date);

	TrainDetails findByTrainNumber(int trainNumber);



}
