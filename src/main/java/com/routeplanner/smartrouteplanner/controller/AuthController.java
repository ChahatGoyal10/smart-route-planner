package com.routeplanner.smartrouteplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.routeplanner.smartrouteplanner.model.User;
import com.routeplanner.smartrouteplanner.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }


    // LOGIN
    @PostMapping("/login")
public String login(@RequestBody User loginRequest){

    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

    if(user.isEmpty()){
        return "❌ Incorrect email or password";
    }

    User existingUser = user.get();

    if(passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())){
        return "✅ Successfully logged in";
    }

    return "❌ Incorrect email or password";
}
}