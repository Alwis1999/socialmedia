package com.example.socialmedia.controller;

import com.example.socialmedia.entity.AuthRequest;
import com.example.socialmedia.entity.UserInfo;
import com.example.socialmedia.service.JwtService;
import com.example.socialmedia.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userInforService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this end point not protected";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserInfo userInfo) {
        return userInforService.signUp(userInfo);
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to userProfile";
    }

    @GetMapping("/user/Admin")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(), authRequest.getUserPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        }
        else {
            throw new UsernameNotFoundException("Invalied User name");
        }

    }
}
