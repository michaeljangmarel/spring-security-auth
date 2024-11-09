package org.example.springsecurityrest.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityrest.dto.JwtResponse;
import org.example.springsecurityrest.dto.LoginRequest;
import org.example.springsecurityrest.dto.SignUpRequest;
import org.example.springsecurityrest.entity.AccountEntity;
import org.example.springsecurityrest.entity.Role;
import org.example.springsecurityrest.repo.AccountRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final AccountRepo accountRepo;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AccountEntity signUp (SignUpRequest signUpRequest) {
        AccountEntity accountEntity = new AccountEntity(null , signUpRequest.username() , passwordEncoder.encode(signUpRequest.password()), signUpRequest.email() , Role.USER);
        return accountRepo.save(accountEntity);
    }

    public JwtResponse login (LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email() , loginRequest.password()));
        var user = accountRepo.findByEmail(loginRequest.email()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwt = jwtService.generateToken(user);
        var rft = jwtService.refreshToken(new HashMap<>() , user);
        JwtResponse jwtResponse = new JwtResponse(jwt , rft);
        return  jwtResponse ;
    }
}
