package com.hassan.Controller;


import com.hassan.Model.Users;
import com.hassan.Record.RegisterRecord;
import com.hassan.Record.UsersRecord;
import com.hassan.Repo.UsersRepo;
import com.hassan.Security.JwtService.JwtConfig;
import com.hassan.Service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;



    @PostMapping("register")
    public void register(@RequestBody @Valid RegisterRecord registerRecord) {
        usersService.save(registerRecord);
    }


    @PostMapping("login")
    public String login(@RequestBody Users user) {
        return usersService.login(user);
    }


    @GetMapping
    public List<UsersRecord> findAll() {
        return usersService.findAll();
    }


    @GetMapping("{userName}")
    public UsersRecord findByName(@PathVariable String userName) {
        return usersService.findByName(userName);
    }


    @PutMapping
    public void update(@RequestBody Users user) {
        usersService.update(user);
    }




}
