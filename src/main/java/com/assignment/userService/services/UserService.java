package com.assignment.userService.services;

import com.assignment.userService.user.AppUser;

import java.util.List;

public interface UserService {

    AppUser createUser(AppUser product);

    AppUser updateUser(AppUser product);

    List<AppUser> getAllUser();

    AppUser getUserById(long productId);

    void deleteUser(long id);
}
