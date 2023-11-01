package com.ricksonrocha.todolist.model.task;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.ricksonrocha.todolist.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name="task")
@Table(name="task")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;

    @NotBlank
    private String description;
    
    @CreationTimestamp
    private LocalDate startAt;

    @CreationTimestamp
    private LocalDate endAt;
     
    private boolean taskDone;

    //private Priority priority
    @ManyToOne
    private  User user;
}
