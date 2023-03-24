package com.alpergayretoglu.todo_app.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Entity
@Table(
        name = "users",
        uniqueConstraints = { // we had to put this here because 2 @Column annotations are not allowed
                @UniqueConstraint(columnNames = "email")
        })
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    private Long id;
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Role role = Role.USER;

}