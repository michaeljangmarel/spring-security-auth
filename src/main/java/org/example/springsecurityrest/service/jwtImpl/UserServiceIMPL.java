package org.example.springsecurityrest.service.jwtImpl;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityrest.repo.AccountRepo;
import org.example.springsecurityrest.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL  implements UserService {

    private  final AccountRepo accountRepo;

    @Override
    public UserDetailsService  userDetailsService (){
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return  accountRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
            }
        };
    }

}
