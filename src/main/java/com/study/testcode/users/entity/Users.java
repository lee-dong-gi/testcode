package com.study.testcode.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String email;

    @Builder
    public Users(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
