package com.groceteria.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.User;


@Repository
public interface  UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmailIdAndPassword(String emailId, String password);
	Optional<User>  findByEmailId(String emailId);
	
}
