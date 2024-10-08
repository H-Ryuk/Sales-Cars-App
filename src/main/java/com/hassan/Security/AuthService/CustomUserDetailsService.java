package com.hassan.Security.AuthService;

import com.hassan.Exception.LogInFailedException;
import com.hassan.Model.Users;
import com.hassan.Repo.UsersRepo;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmail(email)
                .orElse(new Users());

        System.out.println("load User by username get called " + email);
        return new CustomUserDetails(user);
    }


}
