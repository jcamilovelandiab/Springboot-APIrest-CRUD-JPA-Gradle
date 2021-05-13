package com.intership.bank.services;

import com.intership.bank.entities.User;
import com.intership.bank.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.intership.bank.services.interfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Component(value = "UserServiceImpl")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(User user){
    	userRepository.delete(user);
    }
    
    public Optional<User> getUserByNid(String nid) {
    	return userRepository.findByNid(nid);
    }
    
    public Optional<User> getUserByNidForRead(String nid) {
    	return userRepository.findByNidForRead(nid);
    }
    
}