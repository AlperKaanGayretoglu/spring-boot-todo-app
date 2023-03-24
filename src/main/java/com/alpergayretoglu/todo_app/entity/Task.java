package com.alpergayretoglu.todo_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_generator",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_generator"
    )
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate dueDate;
    private LocalDate completedDate;
    @ManyToOne
    private User owner;

    public Long getRemainingDays() {
        return Math.max(0, ChronoUnit.DAYS.between(LocalDateTime.now(), getDueDate()));
    }

}