package com.example.study.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        if (request.email() == null || request.email().isBlank()
                || request.password() == null || request.password().isBlank()
                || request.name() == null || request.name().isBlank()) {
            return ResponseEntity.badRequest().body("email, password, name is required");
        }
        UserDto existing = authMapper.findByEmail(request.email());
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email already exists");
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        authMapper.insertUser(request.email(), encodedPassword, request.name());
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        if (request.email() == null || request.email().isBlank()
                || request.password() == null || request.password().isBlank()) {
            return ResponseEntity.badRequest().body("email and password is required");
        }
        UserDto user = authMapper.findByEmail(request.email());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
        if (!passwordEncoder.matches(request.password(), user.passwordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
        session.setAttribute(LOGIN_USER_ID, user.id());
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object loginUserId = session.getAttribute(LOGIN_USER_ID);
        if (loginUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
        Long userId = (Long) loginUserId;
        UserDto user = authMapper.findById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
        return ResponseEntity.ok(new MeResponse(user.id(), user.email(), user.name()));
    }
}
