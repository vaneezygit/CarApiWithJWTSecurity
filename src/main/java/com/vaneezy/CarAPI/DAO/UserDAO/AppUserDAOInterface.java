package com.vaneezy.CarAPI.DAO.UserDAO;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.AppUser;

public interface AppUserDAOInterface {

    AppUser loadAppUserByUsername(String email);

    void save(AppUser appUser);
}
