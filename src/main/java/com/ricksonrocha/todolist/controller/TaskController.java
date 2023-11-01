package com.ricksonrocha.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ricksonrocha.todolist.model.task.Task;
import com.ricksonrocha.todolist.repository.TaskRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("tasks")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

     public ResponseEntity<Object> created(@RequestBody Task task,
                                UriComponentsBuilder uriBuilder){
     Task taskLocal = taskRepository.save(task);
        var uri = uriBuilder.path("/tasks/{id}").
                  buildAndExpand(taskLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Task>> list(@PageableDefault(size = 4, sort = {"titulo"}) Pageable paginacao) {
        Page<Task> tasks = taskRepository.findAll(paginacao);
        return ResponseEntity.ok(tasks);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id){
        var  task = taskRepository.getReferenceById(id);
        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }
    

}
