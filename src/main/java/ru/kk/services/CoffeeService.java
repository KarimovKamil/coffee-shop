package ru.kk.services;

import ru.kk.exceptions.CoffeeNotFoundException;
import ru.kk.models.Coffee;
import ru.kk.models.Order;

import java.util.List;

public interface CoffeeService {

    int addCoffee(Coffee coffee);

    void updateCoffee(Coffee coffee) throws CoffeeNotFoundException;

    void deleteCoffee(int id);

    Coffee getCoffee(int id);

    List<Coffee> getAllCoffees();

    Order buyCoffee(int id, int count);
}
