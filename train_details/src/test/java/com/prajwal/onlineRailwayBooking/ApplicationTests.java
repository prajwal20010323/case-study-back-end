package com.prajwal.onlineRailwayBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.prajwal.onlineRailwayBooking.model.TrainDetails;
import com.prajwal.onlineRailwayBooking.model.TrainFare;
import com.prajwal.onlineRailwayBooking.repository.RailwayDetailsRepo;
import com.prajwal.onlineRailwayBooking.repository.TrainFareRepo;
import com.prajwal.onlineRailwayBooking.service.TrainDetailsServiceImpl;

@SpringBootTest
class ApplicationTests 
{
	@Mock
    private RailwayDetailsRepo railwayDetailsRepo;
	
	@Mock
    private TrainFareRepo trainFareRepo;

    @InjectMocks
    private TrainDetailsServiceImpl trainDetailsServiceImpl;

    @BeforeEach
    void setup() {
//        MockitoAnnotations.openMocks(this);
        TrainDetails trainDetails = new TrainDetails("1s", 1001,"Duranto","Mumbai","Nagpur","2023-07-01","8:40PM","9:00AM",60,60,0,0, null);

        when(railwayDetailsRepo.save(trainDetails)).thenReturn(trainDetails);

    }
	
	@Test
	void testInsertTrainDetails()
	{
		   // Create a sample TrainDetails object
		TrainDetails trainDetails = new TrainDetails("1s", 1001,"Duranto","Mumbai","Nagpur","2023-07-01","8:40PM","9:00AM",60,60,0,0, null);


        // Mock the behavior of the trainRepository.save() method

        // Call the method under test
        TrainDetails result =  trainDetailsServiceImpl.insertTrainDetails(trainDetails);

        // Verify the result
        assertEquals(trainDetails, result);

        // Verify that the trainRepository.save() method was called once with the correct trainDetails object
//        verify(railwayDetailsRepo, times(1)).save(trainDetails);
	}
	
	
	
	
	@Test
	void testGetTrainDetails()
	{
		   // Create a sample list of TrainDetails objects
        List<TrainDetails> trainDetailsList = new ArrayList<>();
        ArrayList <String> station =  new ArrayList<>();
        station.add("Mumbai");
        station.add("Nagpur");
        trainDetailsList.add(new TrainDetails());
//        trainDetailsList.add(new TrainDetails("Train 2"));

        // Mock the behavior of the trainRepository.findAll() method
        when(railwayDetailsRepo.findAll()).thenReturn(trainDetailsList);

        // Call the method under test
        List<TrainDetails> result = trainDetailsServiceImpl.getTrainDetails();

        // Verify the result
        assertEquals(trainDetailsList, result);

        // Verify that the trainRepository.findAll() method was called once
        verify(railwayDetailsRepo, times(1)).findAll();
    }

	@Test
	void testAddTrainFare()
	{
		// Create a sample TrainFare object
        TrainFare trainFare = new TrainFare();
        trainFare.setTrainNumber(1001);
        trainFare.setStationFrom("Mumbai");
        trainFare.setStationTo("Nagpur");
        trainFare.setSLFare(700);

        // Mock the behavior of the trainFareRepository.insert() method
        when(trainFareRepo.insert(trainFare)).thenReturn(trainFare);

        // Call the method under test
        TrainFare result =  trainDetailsServiceImpl.addTrainFare(trainFare);

        // Verify the result
        assertEquals(trainFare, result);

        // Verify that the trainFareRepository.insert() method was called once with the correct trainFare object
        verify(trainFareRepo, times(1)).insert(trainFare);
	}
	
	@Test
    void testGetAllTrainFare() {
        // Create a sample list of TrainFare objects
        List<TrainFare> trainFareList = new ArrayList<>();
        trainFareList.add(new TrainFare());

        // Mock the behavior of the trainFareRepository.findAll() method
        when(trainFareRepo.findAll()).thenReturn(trainFareList);

        // Call the method under test
        List<TrainFare> result = trainDetailsServiceImpl.getAllTrainFare();

        // Verify the result
        assertEquals(trainFareList, result);

        // Verify that the trainFareRepository.findAll() method was called once
        verify(trainFareRepo, times(1)).findAll();
    }
	
	@Test
    void testUpdateTrainDetails() {
        // Create a sample TrainDetails object
        TrainDetails trainDetails = new TrainDetails();
//        trainDetails.setId();
        trainDetails.setTrainName("Duranto");
        trainDetails.setDepartureStartStationTime("8:40PM");
        trainDetails.setArrivalEndStationTime("9:00AM");

        // Mock the behavior of the trainRepository.save() method
        when(railwayDetailsRepo.save(trainDetails)).thenReturn(trainDetails);

        // Call the method under test
        TrainDetails result = trainDetailsServiceImpl.updateTrainDetails(trainDetails);

        // Verify the result
        assertEquals(trainDetails, result);

        // Verify that the trainRepository.save() method was called once with the correct trainDetails object
        verify(railwayDetailsRepo, times(1)).save(trainDetails);
    }
	
	


}