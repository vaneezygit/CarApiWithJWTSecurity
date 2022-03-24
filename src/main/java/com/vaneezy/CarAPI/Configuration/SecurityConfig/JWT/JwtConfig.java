package com.vaneezy.CarAPI.Configuration.SecurityConfig.JWT;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt.config")
@Configuration
@Data
public class JwtConfig {

    private String header;
    private String prefix;
    private String secret;

}
