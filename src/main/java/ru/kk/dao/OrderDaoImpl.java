package ru.kk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kk.models.Order;

import javax.persistence.EntityManager;

@Repository
public class OrderDaoImpl implements OrderDao {
    private EntityManager entityManager;

    @Autowired
    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }
}
