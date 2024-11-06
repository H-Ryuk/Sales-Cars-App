package com.hassan.Controller;


import com.hassan.Model.Users;
import com.hassan.Record.UserLogInRecord;
import com.hassan.Record.UsersRecord;
import com.hassan.Service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;



    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody @Valid UsersRecord registerRecord) {
        Long userId = usersService.save(registerRecord);
        return new
                ResponseEntity<>("User " + registerRecord.userName() + " created successfully with ID: " + userId,
                HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserLogInRecord user) {
        String token = usersService.login(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UsersRecord>> findAll() {
        List<UsersRecord> userList = usersService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping("{userName}")
    public ResponseEntity<UsersRecord> findByName(@PathVariable String userName) {
        UsersRecord user =  usersService.findByName(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping
    public void update(@RequestBody @Valid UsersRecord user) {
        usersService.update(user);
    }




}
