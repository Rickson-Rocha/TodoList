package com.ricksonrocha.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricksonrocha.todolist.model.user.User;
import com.ricksonrocha.todolist.repository.TaskRepository;
import com.ricksonrocha.todolist.repository.UserRepository;
import java.util.List;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;



    public User findById(long id){
       Optional<User> user = this.userRepository.findById(id);
       return user.orElseThrow(()->new RuntimeException( "User not found! id: "+ id + "Type: " + User.class.getName()));
    }

    public List<User>findAllUser(){
      return this.userRepository.findAll();
    }
    @Transactional
    public User create(User user){
      user.setId(null);
      user = this.userRepository.save(user);
      return user;

    }
    @Transactional
    public User update(User user){
       User newUser = findById(user.getId());
       newUser.setUserpassword(user.getUserpassword());
       return this.userRepository.save(newUser);

    }

    @Transactional
    public void delete(Long id){
      findById(id);
      try {
        userRepository.deleteById(id);
      } catch (Exception e) {
        throw new RuntimeException("It is not possible to delete because there are related entities.");
      }
    }
    
}
