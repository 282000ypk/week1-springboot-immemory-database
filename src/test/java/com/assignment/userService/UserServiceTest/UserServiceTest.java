package com.assignment.userService.UserServiceTest;

import com.assignment.userService.exception.ResourceNotFoundException;
import com.assignment.userService.user.AppUser;
import com.assignment.userService.repositories.UserRepository;
import com.assignment.userService.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserTest(){
        AppUser user1 = new AppUser(1L,"Books", "Reading Material");
        user1 = userService.createUser(user1);
        AppUser user2 = userService.getUserById(user1.getUserId());
        Assertions.assertEquals(user2.getName(), user1.getName());
        Assertions.assertEquals(user2.getEmail(), user1.getEmail());
    }

    @Test
    public void AppupdateUserTest(){
        String newemail = "newsemail@gmail.com";
        AppUser user1 = null;
        Optional<AppUser> usercheck = userRepository.findById(1L);
        if(usercheck.isPresent()) {
            user1 = usercheck.get();
        } else {
            user1 = new AppUser(1L, "New User", "testemail@yahoo.com");
            userService.createUser(user1);
        }
        user1.setEmail(newemail);
        AppUser user2 = userService.updateUser(user1);
        Assertions.assertEquals(user2.getEmail(), newemail);
    }

    @Test
    public void AppdeleteUserTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            AppUser user = null;
            Optional<AppUser> usercheck = userRepository.findById(1L);
            if(usercheck.isPresent()) {
                user = usercheck.get();
            } else {
                user = new AppUser(1L, "New User", "testemail@yahoo.com");
                userService.createUser(user);
            }
            userService.deleteUser(user.getUserId());
            user = userService.getUserById(user.getUserId());
        });
    }
}
