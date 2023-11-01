package com.ricksonrocha.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricksonrocha.todolist.model.task.Task;

public interface taskRepository extends JpaRepository<Task,Long>{
    
}
