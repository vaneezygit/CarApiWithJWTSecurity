package com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("user")
    USER
}
