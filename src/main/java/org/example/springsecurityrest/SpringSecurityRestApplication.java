package org.example.springsecurityrest;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityrest.entity.AccountEntity;
import org.example.springsecurityrest.entity.Role;
import org.example.springsecurityrest.repo.AccountRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityRestApplication {

    private final AccountRepo accountRepo ;
    private  final PasswordEncoder passwordEncoder ;
    @Bean @Profile("dev")
    public ApplicationRunner runner () {
        return args -> {
            AccountEntity accountEntity = new AccountEntity(null , "JM 95" , passwordEncoder.encode("12345") , "region@gmail.com" , Role.ADMIN);
            accountRepo.save(accountEntity);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityRestApplication.class, args);
    }

}
