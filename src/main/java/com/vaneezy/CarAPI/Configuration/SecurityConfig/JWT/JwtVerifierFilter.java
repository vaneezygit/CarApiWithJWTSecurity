package com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtVerifierFilter extends BasicAuthenticationFilter {

    public JwtVerifierFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(token != null){
            String user = JWT.require(Algorithm.HMAC512("TyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebrolet".getBytes()))
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
            String role = JWT.require(Algorithm.HMAC512("TyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebroletTyTyTyEtoBebrolet".getBytes()))
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getClaim("Role").asString();

            if(user != null) return new UsernamePasswordAuthenticationToken(user,null,
                    Collections.singletonList(new SimpleGrantedAuthority(role))
            );
            return null;
        }
        return null;
    }
}
