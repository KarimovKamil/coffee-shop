package ru.kk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kk.models.Coffee;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CoffeeDaoImpl implements CoffeeDao {
    private EntityManager entityManager;

    @Autowired
    public CoffeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Coffee coffee) {
        entityManager.persist(coffee);
    }

    @Override
    public Coffee update(Coffee coffee) {
        return entityManager.merge(coffee);
    }

    @Override
    public void delete(int id) {
        entityManager.createQuery("delete from Coffee c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Coffee getById(int id) {
        return entityManager.find(Coffee.class, id);
    }

    @Override
    public List<Coffee> getAll() {
        return entityManager.createQuery("from Coffee c order by c.sort").getResultList();
    }

    @Override
    public boolean existsBySort(String sort) {
        return entityManager.createQuery("select 1 from Coffee c where c.sort = :sort")
                .setParameter("sort", sort).getResultList().size() != 0;
    }

    @Override
    public boolean existsBySortExcept(String sort, int id) {
        return entityManager.createQuery("select 1 from Coffee c where c.sort = :sort and c.id <> :id")
                .setParameter("sort", sort).setParameter("id", id).getResultList().size() != 0;
    }

    @Override
    public boolean coffeeExistsById(int id) {
        return entityManager.createQuery("select 1 from Coffee c where c.id = :id")
                .setParameter("id", id).getResultList().size() != 0;
    }
}
