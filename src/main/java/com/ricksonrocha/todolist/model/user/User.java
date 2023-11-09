package com.ricksonrocha.todolist.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricksonrocha.todolist.model.task.Task;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="user")
@Table(name="user")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "user_name", length = 100, nullable = false, unique = true)
    @Size(min=5,max=100)
    private String username;
    
    @NotEmpty
    @Column(name = "user_password", length = 60, nullable = false)
    @Size(min=5,max=100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userpassword;
    
    @OneToMany(mappedBy = "user")
    private List <Task> tasks = new ArrayList<Task>();

    public User(Long id, String username, String userpassword) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return this.userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void removeTask(Task task){
        tasks.remove(task);
    }
    
}
