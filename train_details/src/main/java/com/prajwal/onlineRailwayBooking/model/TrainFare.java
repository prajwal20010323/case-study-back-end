package com.prajwal.onlineRailwayBooking.model;

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

@Document(collection = "train_fare")	
public class TrainFare 
{
	@Id
	private String id;
	private int trainNumber;
	private String trainName;
	private String stationFrom;
	private String stationTo;
	private String arrivalTime;
	private String departureTime;
	private String destinationArrivalTime;
	private int ACFare;
	private int SLFare;
	
	
		
	
}
