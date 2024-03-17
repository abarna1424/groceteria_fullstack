package com.groceteria.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.UserRepository;
import com.groceteria.service.UserService;

@Service
public class UserServiceImpl implements UserService{
@Autowired
	
	private UserRepository userRepository;
	
    
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//ADD USER
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	
//	@Override
//	public User loginUser(User user) {
//		return this.userRepository.findByEmailIdAndPassword(user.emailId,user.password).orElseThrow(()->new ResourceNotFoundException("User ", "Id",user.emailId+" and password "+user.password ));
//	
//	}
	
	//LOGIN USER
	@Override
	public User loginUser(User user) {
		return this.userRepository.findByEmailIdAndPassword(user.emailId, user.password).orElseThrow(()->new ResourceNotFoundException("User", "Id",user.emailId+" and password "+user.password));
	}
	
	//UPDATE USER
	@Override
	public User updateUser(User user, Integer userId) {
		User existingUser=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		existingUser.setDistrict(user.getDistrict());
		existingUser.setState(user.getState());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		existingUser.setAddress(user.getAddress());
		existingUser.setGender(user.getGender());
		existingUser.setEmailId(user.getEmailId());
		existingUser.setZipcode(user.getZipcode());
		existingUser.setPassword(user.getPassword());
		userRepository.save(existingUser);
		return existingUser;
	}
	
	//GET USER BY ID
	@Override
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
	}
	
	//GET ALL USER
	@Override
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	//GET USER BY EMAIL
	@Override
	public User getUserByEmail(User user) {
		return null;
	}
	
	//DELETE USER BY ID
	@Override
	public void deleteUser(Integer userId) {
		userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
	    userRepository.deleteById(userId);
	}


}
