package com.ricksonrocha.todolist.model.task;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.ricksonrocha.todolist.enums.Priority;
import com.ricksonrocha.todolist.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @Column(name = "task_title", length = 100, nullable = false, unique = true)
    private String title;

    @NotBlank

    @Column(name = "task_description", length = 100, nullable = false)
    private String description;
    
    @CreationTimestamp
    @Column(name="task_start")
    private LocalDate startAt;

    @CreationTimestamp
    @Column(name="task_end")
    private LocalDate endAt;
     
    @Column(name="task_done")
    private boolean taskDone;

    @Column(name="task_priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private  User user;
}
