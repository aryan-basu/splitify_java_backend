package com.aryan.splitify.Services;

import com.aryan.splitify.Entity.User;
import com.aryan.splitify.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


 @Autowired
   private UserRepository userRepository;


   public void saveNewUser(User user){

       userRepository.save(user);
   }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
