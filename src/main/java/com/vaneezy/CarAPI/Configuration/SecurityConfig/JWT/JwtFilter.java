package com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.sql.Date;

@RequiredArgsConstructor
public class JwtFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AppUser appUser =
                    new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    appUser.getUsername(),
                    appUser.getPassword()
            );
            return  authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withClaim("Role", authResult.getAuthorities().stream()
                        .map(Object::toString)
                        .findAny().get())
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(1)))
                .sign(Algorithm.HMAC512("TyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebrolet".getBytes()));

        response.addHeader("Authentication", "Bearer " + token);
    }
}
