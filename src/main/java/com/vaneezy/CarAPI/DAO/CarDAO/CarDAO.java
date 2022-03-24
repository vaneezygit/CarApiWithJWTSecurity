package com.vaneezy.CarAPI.DAO.CarDAO;

import com.vaneezy.CarAPI.Entity.Car;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component @RequiredArgsConstructor
public class CarDAO implements CarDAOInterface{

    private final SessionFactory sessionFactory;

    @Override
    public List<Car> getAll() {
        Session session = sessionFactory.openSession();
        Query<Car> carListQuery = session.createQuery("FROM Car", Car.class);

        List<Car> resultList = carListQuery.getResultList();

        session.close();
        return resultList;
    }

    @Override
    public Car getById(Long id) {
        Session session = sessionFactory.openSession();
        Query<Car> carById = session.createQuery("FROM Car c " +
                "WHERE c.id = :id", Car.class);
        carById.setParameter("id", id);
        Car car = carById.getResultStream()
                .findAny()
                .orElseThrow(
                        () -> new IllegalStateException(String.format("Car with this id %d doesn't exists", id))
                );
        session.close();
        return car;
    }

    @Override
    public void update(Car car, Long id) {
        Transaction transaction = null;
        Map<String, Object> fieldMap = CarValidator.validateCar(car);
        Car carToUpdate = getById(id);
        for(String key : fieldMap.keySet()){
            switch (key){
                case "model" -> carToUpdate.setModel((String) fieldMap.get(key));

                case "carBrand" -> carToUpdate.setCarBrand((String) fieldMap.get(key));

                case "price" -> carToUpdate.setPrice((BigDecimal) fieldMap.get(key));

                case "carType" -> carToUpdate.setCarType((Car.CarType) fieldMap.get(key));

                case "engineType" -> carToUpdate.setEngineType((Car.EngineType) fieldMap.get(key));
            }
        }
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(carToUpdate);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            Objects.requireNonNull(transaction).rollback();
        }
    }

    @Override
    public void save(Car car) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            Objects.requireNonNull(transaction).rollback();
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Car carToDelete = getById(id);
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(carToDelete);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            Objects.requireNonNull(transaction).rollback();
        }
    }
}
