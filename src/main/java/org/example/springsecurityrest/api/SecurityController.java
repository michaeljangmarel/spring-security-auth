package org.example.springsecurityrest.api;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityrest.dto.JwtResponse;
import org.example.springsecurityrest.dto.LoginRequest;
import org.example.springsecurityrest.dto.SignUpRequest;
import org.example.springsecurityrest.entity.AccountEntity;
import org.example.springsecurityrest.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class SecurityController {

    private  final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AccountEntity> signUp(@RequestBody SignUpRequest signUpRequest) {
       return ResponseEntity.ok(   authService.signUp(signUpRequest));
    }

    @PostMapping("/signIn")
    public JwtResponse  signIn(@RequestBody LoginRequest loginRequest) {
        return  authService.login(loginRequest);
    }
}
