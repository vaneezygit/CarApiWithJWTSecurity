package com.vaneezy.CarAPI.Configuration.HibernateConfig;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.AppUser;
import com.vaneezy.CarAPI.Entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory createSessionFactory(){
        Properties properties = new Properties();
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "2873");
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL,"jdbc:postgresql://localhost:5432/Car");
        properties.put(Environment.HBM2DDL_AUTO,"create-drop");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.FORMAT_SQL, true);

        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(Car.class);
        cfg.addAnnotatedClass(AppUser.class);

        return cfg.buildSessionFactory();
    }
}
