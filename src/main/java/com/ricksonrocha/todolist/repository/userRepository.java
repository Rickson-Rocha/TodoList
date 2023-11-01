package com.ricksonrocha.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricksonrocha.todolist.model.user.User;

@Repository
public interface userRepository extends JpaRepository<User,Long> {
    
}
