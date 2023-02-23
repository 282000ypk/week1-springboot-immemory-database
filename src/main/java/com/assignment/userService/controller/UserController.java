package com.assignment.userService.controller;

import com.assignment.userService.exception.ResourceNotFoundException;
import com.assignment.userService.services.UserService;
import com.assignment.userService.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<AppUser>getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(userService.getUserById(id));
        }
        catch (ResourceNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user")
    public AppUser createUser(@RequestBody AppUser product){
        return userService.createUser(product);
    }

    @PutMapping("/appUser/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable long id, @RequestBody AppUser appUser){
        try {
            ResponseEntity.ok().body(userService.getUserById(id));
            appUser.setUserId(id);
            return ResponseEntity.ok().body(userService.updateUser(appUser));
        }
        catch (ResourceNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        catch (ResourceNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
