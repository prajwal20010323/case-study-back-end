package com.prajwal.onlineRailwayBooking.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Document(collection = "train_details")	
public class TrainDetails 
{
	
	@Id		
	private String id;
	private int trainNumber;
	private String trainName;
	private String startStation;						
	private String endStation;
	private String date;
	private String departureStartStationTime;
	private String arrivalEndStationTime;
	private int ACAvailableSeats;
	private int SLAvailableSeats;
	private int SLWaiting;
	private int ACWaiting; 
	List<String> stations;

	
}
