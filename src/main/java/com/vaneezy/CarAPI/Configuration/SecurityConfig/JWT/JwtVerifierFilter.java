package com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Setter;
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

@Setter
public class JwtVerifierFilter extends BasicAuthenticationFilter {

    private JwtConfig jwtConfig;

    public JwtVerifierFilter(JwtConfig jwtConfig,AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.setJwtConfig(jwtConfig);
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader(jwtConfig.getHeader());
        if(authHeader == null || !authHeader.startsWith(jwtConfig.getPrefix())){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(jwtConfig.getHeader());

        if(token != null){
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(jwtConfig.getSecret().getBytes()))
                    .build()
                    .verify(token.replace(jwtConfig.getPrefix(), ""));

            String user = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("Role").asString();

            if(user != null) return new UsernamePasswordAuthenticationToken(user,null,
                    Collections.singletonList(new SimpleGrantedAuthority(role))
            );
            return null;
        }
        return null;
    }
}
