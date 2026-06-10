package fr.calum51.PersonalProductivity.controller;

import fr.calum51.PersonalProductivity.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody Map<String, String> request
    ) {
        String token = authService.register(
                request.get("username"),
                request.get("email"),
                request.get("password")
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Map<String, String> request
    ) {
        String token = authService.login(
                request.get("username"),
                request.get("password")
        );
        return ResponseEntity.ok(token);
    }
}
