package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.entity.User;
import com.groceteria.repository.UserRepository;
import com.groceteria.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/users")
public class UserController {
	
	@Autowired
	
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Valid@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.CREATED);
	}
	
	//LOGIN
	@PostMapping("/api/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.loginUser(user),HttpStatus.OK);
	}
	
	//UPDATE CUSTOMER
	@PutMapping("user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer userId,@RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);
	}
	
	//GET ALL CUSTOMER
	@GetMapping("/all")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	//GET CUSTOMER BY EMAIL
	@PostMapping("/forgotpassword")
	public User getUserByEmail(@RequestBody User user) {
		return userRepository.findByEmailId(user.getEmailId()).get();
	}
	
	//GET CUSTOMER BY ID
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId){
		return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	//DELETE CUSTOMER
	@DeleteMapping("user/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id")Integer userId){
		userService.deleteUser(userId);
		boolean flag=true;
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
	
	//CHANGE PASSWORD
	@PostMapping("/{uid}/{newpassword}")
	public User changeUserPassword(@PathVariable("uid") Integer uid,@PathVariable("newpassword") String newpassword) {
		User u=userService.getUserById(uid);
		u.setPassword(newpassword);
		userService.updateUser(u, uid);
		return u;
	}
}
