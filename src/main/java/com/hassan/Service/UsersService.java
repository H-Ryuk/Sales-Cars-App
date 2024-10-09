package com.hassan.Service;


import com.hassan.Exception.TargetNotFoundException;
import com.hassan.Model.Users;
import com.hassan.Record.UserLogInRecord;
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



    public Long save(UsersRecord registerRecord) {
        Users user = convertFromObjToUser(registerRecord);
        user.setPassword(bCryptConfig.encoder().encode(user.getPassword()));
        return usersRepo.save(user).getUserId();
    }


    public List<UsersRecord> findAll() {
        return usersRepo.findAll().stream()
                .map(users -> new UsersRecord(
                        users.getUserName(),
                        users.getAddress(),
                        users.getPhoneNumber(),
                        users.getEmail(),
                        users.getPassword(),
                        users.getCin(),
                        users.getRole()
                )).toList();
    }


    public UsersRecord findByName(String name) {
        return usersRepo.findByName(name)
                .orElseThrow(() -> new TargetNotFoundException(name));
    }


    public void update(UsersRecord user) {
        usersRepo.findByEmail(user.email())
                .ifPresentOrElse(users -> {
                            users.setUserName(user.userName());
                            users.setAddress(user.address());
                            users.setPhoneNumber(user.phoneNumber());
                            users.setEmail(user.email());
                            users.setPassword(user.password());
                            users.setCin(user.cin());
                            users.setRole(user.role());
                            usersRepo.save(users);

                        },
                        () -> {
                            throw new TargetNotFoundException(user.userName());
                        }); // create a custom exception
    }




    public String login(UserLogInRecord user) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));

        if (authentication.isAuthenticated())
            return jwtConfig.generateToken(user.email());
        else
            return "failed";
    }




    private Users convertFromObjToUser(UsersRecord registerRecord) {
        Users user = new Users();
        user.setUserName(registerRecord.userName());
        user.setAddress(registerRecord.address());
        user.setPhoneNumber(registerRecord.phoneNumber());
        user.setEmail(registerRecord.email());
        user.setPassword(registerRecord.password());
        user.setCin(registerRecord.cin());
        user.setRole(registerRecord.role());

        return user;
    }


}
