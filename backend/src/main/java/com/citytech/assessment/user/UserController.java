package com.citytech.assessment.user;

import com.citytech.assessment.entity.User;
import com.citytech.assessment.user.auth.dto.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
