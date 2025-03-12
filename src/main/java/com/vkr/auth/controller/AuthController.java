package com.vkr.auth.controller;

import com.vkr.auth.models.Role;
import com.vkr.auth.models.User;
import com.vkr.auth.security.JwtService;
import com.vkr.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request.username(), request.password(), Role.USER);
        return ResponseEntity.ok(jwtService.generateToken(user.getUsername(), user.getRole().name()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.findByUsername(request.username());
        if (user.isPresent() && passwordEncoder.matches(request.password(), user.get().getPassword())) {
            return ResponseEntity.ok(jwtService.generateToken(user.get().getUsername(), user.get().getRole().name()));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    public record RegisterRequest(String username, String password) {}
    public record LoginRequest(String username, String password) {}

}
