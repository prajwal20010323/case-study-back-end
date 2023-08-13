package com.example.trainBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trainBooking.model.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

	List<UserDetails> findByName(String string);

	


}
		