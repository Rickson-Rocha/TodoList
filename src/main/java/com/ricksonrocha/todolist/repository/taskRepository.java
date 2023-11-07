package com.ricksonrocha.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricksonrocha.todolist.model.task.Task;
import java.util.List;
public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByUser_Id(Long id);
}
