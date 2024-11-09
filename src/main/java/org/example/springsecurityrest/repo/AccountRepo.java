package org.example.springsecurityrest.repo;


import org.example.springsecurityrest.entity.AccountEntity;
import org.example.springsecurityrest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepo  extends JpaRepository<AccountEntity , Integer> {

    Optional<AccountEntity> findByEmail(String email);

    AccountEntity findByRole(Role role);
}
