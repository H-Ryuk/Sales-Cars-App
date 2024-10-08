package com.hassan.Controller;


import com.hassan.Model.Users;
import com.hassan.Record.UserLogInRecord;
import com.hassan.Record.UserRegisterRecord;
import com.hassan.Record.UsersRecord;
import com.hassan.Service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;



    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterRecord registerRecord) {
        Long userId = usersService.save(registerRecord);
        return new
                ResponseEntity<>("User " + registerRecord.userName() + " created successfully with ID: " + userId,
                HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogInRecord user) {
        String token = usersService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
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
