package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.User;

public interface UserService {
	User saveUser(User user);
	User loginUser(User user);
	User updateUser(User user,Integer userId);
	User getUserById(Integer userId);
	List<User> getAllUser();
	User getUserByEmail(User user);
	void deleteUser(Integer userId);
}
