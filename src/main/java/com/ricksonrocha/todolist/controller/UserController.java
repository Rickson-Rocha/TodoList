package com.ricksonrocha.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.ricksonrocha.todolist.model.user.User;
import com.ricksonrocha.todolist.repository.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> created(@RequestBody User user,
            UriComponentsBuilder uriBuilder) {
        User userLocal = userRepository.save(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping
    public ResponseEntity<Page<User>> list(@PageableDefault(size = 4, sort = {"username"}) Pageable paginacao) {
        Page<User> users = userRepository.findAll(paginacao);
        return ResponseEntity.ok(users);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<User> uptade(@RequestBody User user) {
        User userLocal = userRepository.findById(
                user.getId()).get();

        userLocal.setUsername(user.getUsername());

        return ResponseEntity.ok(userLocal);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
