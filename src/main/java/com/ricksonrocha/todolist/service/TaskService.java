package com.ricksonrocha.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricksonrocha.todolist.model.task.Task;
import com.ricksonrocha.todolist.model.user.User;
import com.ricksonrocha.todolist.repository.TaskRepository;
import java.util.List;
import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(()-> new RuntimeException("Task not found"));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks= taskRepository.findByUser_Id(userId);
        return tasks;

    }
    @Transactional
    public Task create(Task task){
        User user = this.userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = taskRepository.save(task);
        return task;
    }

    @Transactional
    public Task update(Task task){
        Task newTask = findById(task.getId());
        newTask.setTitle(task.getTitle());
        newTask.setDone(task.getDone());
        newTask.setDescription(task.getDescription());
        newTask.setPriority(task.getPriority());

        return this.taskRepository.save(newTask);
    }
    public void delete(Long id){
        findById(id);
        this.taskRepository.deleteById(id);
    }
}
