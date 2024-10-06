package com.hassan.Service;


import com.hassan.Model.Users;
import com.hassan.Record.UsersRecord;
import com.hassan.Repo.UsersRepo;
import com.hassan.Security.Encryption.BCryptConfig;
import com.hassan.Security.JwtService.JwtConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepo usersRepo;
    private final BCryptConfig bCryptConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;




    public void save(Users user) {
        user.setPassword(bCryptConfig.encoder().encode(user.getPassword()));
        usersRepo.save(user);
    }



    public List<UsersRecord> findAll() {
        return usersRepo.findAll().stream()
                .map(users -> new UsersRecord(
                        users.getUserName(),
                        users.getAddress(),
                        users.getPhoneNumber(),
                        users.getEmail(),
                        users.getCin(),
                        users.getRole()
                )).toList();
    }



    public UsersRecord findByName(String name) {
        return usersRepo.findByName(name)
                .orElseThrow(); // create a custom exception
    }



    public void update(Users user) {
        usersRepo.findByEmail(user.getEmail())
                .ifPresentOrElse(users -> {
                            users.setUserName(user.getUserName());
                            users.setAddress(user.getAddress());
                            users.setPhoneNumber(user.getPhoneNumber());
                            users.setEmail(user.getEmail());
                            users.setPassword(user.getPassword());
                            users.setCin(user.getCin());
                            users.setRole(user.getRole());
                            usersRepo.save(users);

                        },
                        () -> {}); // create a custom exception
    }




    public String login(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtConfig.generateToken(user.getEmail());
        return "Failed";
    }


}
