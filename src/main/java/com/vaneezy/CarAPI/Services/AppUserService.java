package com.vaneezy.CarAPI.Services;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.AppUser;
import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.RegistrationRequest;
import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.Role;
import com.vaneezy.CarAPI.DAO.UserDAO.AppUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service @RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserDAO appUserDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserDAO.loadAppUserByUsername(username);
        if(appUser == null) return null;
        User user = new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole().name()))
        );
        return user;
    }

    public void saveUser(RegistrationRequest request) {
        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        appUserDAO.save(user);
    }
}
