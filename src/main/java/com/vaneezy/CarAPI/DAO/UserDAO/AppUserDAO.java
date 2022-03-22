package com.vaneezy.CarAPI.DAO.UserDAO;

import com.vaneezy.CarAPI.Configuration.SecurityConfig.ApplicationUser.AppUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component @RequiredArgsConstructor
public class AppUserDAO implements AppUserDAOInterface {

    private final SessionFactory sessionFactory;

    @Override
    public AppUser loadAppUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query<AppUser> appUserByEmail = session.createQuery("FROM app_user u " +
                "WHERE u.username = :username", AppUser.class);
        appUserByEmail.setParameter("username", username);
        AppUser appUser = appUserByEmail.getResultStream()
                .findAny()
                .orElse(null);
        session.close();
        return appUser;
    }

    @Override
    public void save(AppUser appUser) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(appUser);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            Objects.requireNonNull(transaction).rollback();
        }
    }
}
