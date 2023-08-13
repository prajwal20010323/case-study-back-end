package com.prajwal.onlineRailwayBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.onlineRailwayBooking.exception.TainDetailsException;
import com.prajwal.onlineRailwayBooking.model.TrainDetails;
import com.prajwal.onlineRailwayBooking.model.TrainFare;
import com.prajwal.onlineRailwayBooking.repository.RailwayDetailsRepo;
import com.prajwal.onlineRailwayBooking.repository.TrainFareRepo;

@Service
public class TrainDetailsServiceImpl implements TrainDetailsService
{
	
	
	@Autowired
	private RailwayDetailsRepo repository;
	
	@Autowired
	private TrainFareRepo trainFareRepo;
	
	
	public TrainDetails insertTrainDetails(TrainDetails trainDetails)
	{
		return repository.save(trainDetails);
	}

	

	public List<TrainDetails> getTrainDetails()
	{
		return  repository.findAll();
	}


	public TrainFare addTrainFare(TrainFare trainFare)
	{
		return trainFareRepo.insert(trainFare);
	}
	
	public List<TrainFare> getAllTrainFare()
	{	
		return trainFareRepo.findAll();
	}
	
	public TrainDetails updateTrainDetails(TrainDetails trainDetails)
	{
		return repository.save(trainDetails);
	}


	public List<TrainFare> searchTrain(String from, String to, String date) throws TainDetailsException{
		List<TrainFare> obj1 = trainFareRepo.findByStationFromAndStationTo(from , to);
		int trainNumber = 0;
		
		if(obj1.isEmpty()) {
			throw new TainDetailsException("Train Not found");		
		}
		for( TrainFare objectTemp  : obj1)
		{
			trainNumber = objectTemp.getTrainNumber();
		}
		
		
		
		List<TrainDetails> obj2 = repository.findByDateAndTrainNumber(date,trainNumber);
		List<TrainFare> obj3 = null;
		if(obj1 != null && obj2 != null && date.equals(obj2.get(0).getDate()))
		{
			return obj1 ;
		}
//		return (List<TrainFare>) null;
		return obj3;
	}



	@Override
	public TrainDetails getTrainByTrainNumberAndDate(int trainNumber, String date) {
		return repository.findByTrainNumberAndDate(trainNumber,date);
	}






	@Override
	public TrainFare getTrainFareByTrainNumberAndStationFromStationTo(int trainNumber, String from, String to) {
		return trainFareRepo.findByTrainNumberAndStationFromAndStationTo(trainNumber,from,to);
	}



	@Override
	public TrainFare getTrainFareByTrainNumber(int trainNumber) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public TrainDetails updateTrainDetails(int trainNumber, String trainClass,String bookingStatus) 
	{

		TrainDetails trainDetails = repository.findByTrainNumber(trainNumber);
		int ACAvailableSeats = trainDetails.getACAvailableSeats();
		int SLAvailableSeats = trainDetails.getSLAvailableSeats();
		int SLWaiting = trainDetails.getSLWaiting();
		int ACWaiting = trainDetails.getACWaiting();
		String id = trainDetails.getId();
		System.out.println(id);
//		repository.deleteById(id);
//		if(repository.findById(id) == null)
//		{
//			System.out.println("delete");
//		}else {
//			System.out.println("failed to delete");
//		}
//		
		System.out.println(trainDetails);
		if(trainClass.equals("SL"))
		{
			if(bookingStatus.equals("Confirm"))
			{
				SLAvailableSeats = SLAvailableSeats - 1;
				trainDetails.setSLAvailableSeats(SLAvailableSeats);
				return repository.save(trainDetails);
			}
			else {
				SLWaiting = SLWaiting + 1;
				trainDetails.setSLWaiting(SLWaiting);
				return repository.save(trainDetails);
			}
			
		}
		
		else
		{
			if(bookingStatus.equals("Confirm"))
			{
				ACAvailableSeats = ACAvailableSeats - 1;
				trainDetails.setACAvailableSeats(ACAvailableSeats);
				return repository.save(trainDetails);
			}
			else {
				ACWaiting = ACWaiting + 1;
				trainDetails.setACWaiting(ACWaiting);
				return repository.save(trainDetails);
			}
			
		}
//		return null;
	}
	
}
	