package com.ricksonrocha.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ricksonrocha.todolist.model.task.Task;
import com.ricksonrocha.todolist.repository.TaskRepository;
import com.ricksonrocha.todolist.service.TaskService;

import jakarta.transaction.Transactional;
import java.util.List;
@RestController
@RequestMapping("tasks")
@Validated
public class TaskController {
    
    @Autowired
    private TaskService  taskService;
  
   @GetMapping("/{id}")
   public ResponseEntity<Task> findById(@PathVariable Long id){
    Task task = this.taskService.findById(id);
    return ResponseEntity.ok(task);
   }
   @GetMapping("/users/{userId}")
   public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId){
     var tasks = taskService.findAllByUserId(userId);
     return ResponseEntity.ok(tasks);
   }
//    public ResponseEntity<Page<Task>> findAll(@PageableDefault(size = 4, sort = { "title" }) Pageable paginacao) {
//         var users = this.taskRepository.findAll(paginacao);
//         return ResponseEntity.ok(users);
//     }
    @PostMapping
    @Transactional
    public ResponseEntity<Void>create(@Validated @RequestBody Task task,UriComponentsBuilder uriBuilder){
        Task createdTask = taskService.create(task); 
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(createdTask.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> update(@Validated  @RequestBody  Task task , @PathVariable Long id){
         task.setId(id);
         Task createdTask = this.taskService.update(task);
         return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();

    }
}


