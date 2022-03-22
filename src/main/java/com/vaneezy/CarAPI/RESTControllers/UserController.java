package com.vaneezy.CarAPI.RESTControllers;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.RegistrationRequest;
import com.vaneezy.CarAPI.Services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
public class UserController {

    private final AppUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest request){
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        if(user != null) return ResponseEntity.badRequest().body(String.format("User with this username: %s already exists", request.getUsername()));
        userService.saveUser(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
