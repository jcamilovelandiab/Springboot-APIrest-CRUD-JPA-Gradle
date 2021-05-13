package com.intership.bank.services.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.intership.bank.entities.User;

@Service
public interface IUserService {
	
	public User createUser(User user);
	public List<User> getAllUsers();
	public Optional<User> getUserById(Long id);
	public void deleteUser(User user);
	public Optional<User> getUserByNid(String nid);
	public Optional<User> getUserByNidForRead(String nid);
	
}
