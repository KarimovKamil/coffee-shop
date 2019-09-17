package ru.kk.services;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kk.dao.CoffeeDao;
import ru.kk.dao.OrderDao;
import ru.kk.exceptions.CoffeeNotFoundException;
import ru.kk.exceptions.IncorrectDataException;
import ru.kk.models.Coffee;
import ru.kk.models.Order;
import ru.kk.validation.CoffeeValidator;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class CoffeeServiceImpl implements CoffeeService {
    private CoffeeDao coffeeDao;
    private OrderDao orderDao;
    private CoffeeValidator coffeeValidator;

    @Autowired
    public CoffeeServiceImpl(CoffeeDao coffeeDao, OrderDao orderDao, CoffeeValidator coffeeValidator) {
        this.coffeeDao = coffeeDao;
        this.orderDao = orderDao;
        this.coffeeValidator = coffeeValidator;
    }

    @Override
    public int addCoffee(Coffee coffee) {
        coffeeValidator.checkNewCoffee(coffee);
        coffee.setSort(coffee.getSort().toUpperCase());
        coffeeDao.add(coffee);
        return coffee.getId();
    }

    @Override
    public void updateCoffee(Coffee coffee) {
        coffeeValidator.checkUpdatingCoffee(coffee);
        coffee.setSort(coffee.getSort().toUpperCase());
        coffeeDao.update(coffee);
    }

    @Override
    public void deleteCoffee(int id) {
        coffeeDao.delete(id);
    }

    @Override
    public Coffee getCoffee(int id) {
        Coffee coffee = coffeeDao.getById(id);
        if (coffee == null) {
            throw new CoffeeNotFoundException();
        }
        return coffee;
    }

    @Override
    public List<Coffee> getAllCoffees() {
        return coffeeDao.getAll();
    }

    @Override
    public Order buyCoffee(int id, int count) {
        Coffee coffee = coffeeDao.getById(id);
        if (coffee == null) {
            throw new CoffeeNotFoundException();
        }
        if (coffee.getCount() < count) {
            throw new IncorrectDataException("Amount of exists coffee not enough");
        }
        coffee.setCount(coffee.getCount() - count);
        Order order = Order.builder()
                .coffee(coffee)
                .count(count)
                .date(new Date(System.currentTimeMillis()))
                .build();
        coffeeDao.update(coffee);
        orderDao.add(order);
        return order;
    }
}
