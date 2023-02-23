package com.assignment.userService.services;

import com.assignment.userService.exception.ResourceNotFoundException;
import com.assignment.userService.repositories.UserRepository;
import com.assignment.userService.user.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public AppUser createUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public AppUser updateUser(AppUser appUser) {
        Optional<AppUser> user1 = this.userRepository.findById(appUser.getUserId());

        if(user1.isPresent()) {
            AppUser appUserUpdate = user1.get();
            appUserUpdate.setUserId(appUser.getUserId());
            appUserUpdate.setName(appUser.getName());
            appUserUpdate.setEmail(appUser.getEmail());
            userRepository.save(appUserUpdate);
            return appUserUpdate;
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + appUser.getUserId());
        }
    }

    @Override
    public List<AppUser> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUserById(long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new ResourceNotFoundException("Record not found with id: " + userId);
        }
    }

    @Override
    public void deleteUser(long userId) {
        Optional<AppUser> user1 = this.userRepository.findById(userId);

        if(user1.isPresent()) {
            this.userRepository.delete(user1.get());
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + userId);
        }
    }
}
