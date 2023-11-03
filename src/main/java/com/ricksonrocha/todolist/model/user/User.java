package com.ricksonrocha.todolist.model.user;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User  {
    
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
    private String userpassword;
    
    @OneToMany(mappedBy = "user")
    private List <Task> tasks = new ArrayList<Task>();


}
