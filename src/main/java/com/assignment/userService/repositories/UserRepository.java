package com.assignment.userService.repositories;

import com.assignment.userService.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

}
