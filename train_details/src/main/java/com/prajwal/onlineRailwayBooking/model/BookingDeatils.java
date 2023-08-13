package com.prajwal.onlineRailwayBooking.model;

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
public class BookingDeatils 
{
	private int trainNumber;
	private String trainName;
	private String stationFrom;
	private String stationTo;
	private String date;
	private String arrivalTime;
	private String departureTime;
	private String destinationArrivalTime;
	private int ACAvailableSeats;
	private int SLAvailableSeats;
	private int SLWaiting;
	private int ACWaiting;
	private int ACFare;
	private int SLFare;
}
