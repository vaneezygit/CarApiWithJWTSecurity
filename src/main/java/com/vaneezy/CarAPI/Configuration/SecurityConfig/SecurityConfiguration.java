package com.vaneezy.CarAPI.Configuration.SecurityConfig;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.Role;
import com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT.JwtFilter;
import com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT.JwtVerifierFilter;
import com.vaneezy.CarAPI.Services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtFilter(authenticationManager()))
                .addFilter(new JwtVerifierFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .anyRequest()
                .authenticated();
    }
}
