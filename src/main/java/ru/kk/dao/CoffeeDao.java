package ru.kk.dao;

import ru.kk.models.Coffee;

import java.util.List;

public interface CoffeeDao {

    void add(Coffee coffee);

    Coffee update(Coffee coffee);

    void delete(int id);

    Coffee getById(int id);

    List<Coffee> getAll();

    boolean existsBySort(String sort);

    boolean existsBySortExcept(String sort, int id);

    boolean coffeeExistsById(int id);

}
