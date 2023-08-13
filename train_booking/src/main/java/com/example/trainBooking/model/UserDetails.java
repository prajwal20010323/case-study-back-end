package com.example.trainBooking.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userData")
public class UserDetails 
{
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="userName")
	private String name;
	@Column(name="age")
	private int age;
	@Column(name="gender")
	private String gender;
	
//	public UserDetails(String name, int age, String gender) {
//		super();
//		this.name = name;
//		this.age = age;
//		this.gender = gender;
//	}
	
	
	
}
