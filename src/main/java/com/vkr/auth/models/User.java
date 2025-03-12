package com.vkr.auth.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getUsername() {
        return this.username;
    }

    public Role getRole() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
