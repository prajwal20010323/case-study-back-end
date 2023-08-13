package com.prajwal.onlineRailwayBooking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prajwal.onlineRailwayBooking.model.TrainFare;

public interface TrainFareRepo extends MongoRepository <TrainFare , String>
{



	List<TrainFare> findByStationFromAndStationTo(String from, String to);

	TrainFare findByTrainNumber(int trainNumber);

	TrainFare findByTrainNumberAndStationFromAndStationTo(int trainNumber, String from, String to);


}
