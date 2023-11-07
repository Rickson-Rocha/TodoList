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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;


@Entity(name="task")
@Table(name="task")
@EqualsAndHashCode(of = "id")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(name = "task_title", length = 100, nullable = false, unique = true)
    private String title;

    @NotEmpty

    @Column(name = "task_description", length = 100, nullable = false)
    private String description;
    
    @CreationTimestamp
    @Column(name="task_start")
    @NotNull
    private LocalDate startAt;

    @CreationTimestamp
    @Column(name="task_end")
    @NotNull
    private LocalDate endAt;
     
    @Column(name="task_done")
    @NotNull
    private boolean done;

    @Column(name="task_priority")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private  User user;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartAt() {
        return this.startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return this.endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public boolean isDone() {
        return this.done;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
