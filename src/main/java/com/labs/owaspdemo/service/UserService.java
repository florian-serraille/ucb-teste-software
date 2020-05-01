package com.labs.owaspdemo.service;

import com.labs.owaspdemo.model.User;
import com.labs.owaspdemo.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(final UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public boolean userExist(String email) {
		User user = new User();
		user.setEmail(email);
		return userRepository.exists(Example.of(user));
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User findUserByMailAndPassword(final String email, final String password) {
		final User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		final Optional<User> optionalUser = userRepository.findOne(Example.of(user));
		return optionalUser.orElse(null);
	}
}