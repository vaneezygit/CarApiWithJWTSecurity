package com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String username;
    private String password;

}
