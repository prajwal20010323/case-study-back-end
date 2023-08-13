package com.example.trainBooking.model;

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

public class Book {
//	{trainNumber}/{date}/{from}/{to}/{trainClass}/{name}/{age}/{gender}
	private int trainNumber;
	private String stationFrom;
	private String stationTo;
//	private String date;
	private String trainClass;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String razorpay_payment_id;
}
