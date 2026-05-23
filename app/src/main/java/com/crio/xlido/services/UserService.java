package com.crio.xlido.services;

import com.crio.xlido.entities.User;
import com.crio.xlido.respositories.IUserRepository;

public class UserService {

    
    private final IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User createUser(String email, String password){
        User user = new User(email, password);
        User newUser = userRepository.save(user);
        return newUser;
    }
    
}
