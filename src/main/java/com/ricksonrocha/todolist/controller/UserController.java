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
import com.ricksonrocha.todolist.model.user.User;
import com.ricksonrocha.todolist.repository.UserRepository;
import com.ricksonrocha.todolist.service.UserService;


@RestController
@RequestMapping("users")
@Validated
public class UserController {
    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<Page<User>> findAll(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
        var users = this.userRepository.findAll(paginacao);
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody User user, UriComponentsBuilder uriBuilder){
        User createdUser = userService.create(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Validated  @RequestBody  User user , @PathVariable Long id){
         user.setId(id);
         User createdUser = this.userService.update(user);
         return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
