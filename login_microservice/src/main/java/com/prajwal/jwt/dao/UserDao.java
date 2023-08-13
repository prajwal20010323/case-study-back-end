package com.prajwal.jwt.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prajwal.jwt.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	Optional<User> findByUserName(String username);
}
