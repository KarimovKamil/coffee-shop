package ru.kk.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kk.dao.CoffeeDao;
import ru.kk.exceptions.CoffeeNotFoundException;
import ru.kk.exceptions.IncorrectDataException;
import ru.kk.models.Coffee;

@Component
public class CoffeeValidator {
    private CoffeeDao coffeeDao;

    @Autowired
    public CoffeeValidator(CoffeeDao coffeeDao) {
        this.coffeeDao = coffeeDao;
    }

    public void checkNewCoffee(Coffee coffee) {
        checkCoffeeCount(coffee.getCount());
        checkCoffeeSort(coffee.getSort());
    }

    public void checkUpdatingCoffee(Coffee coffee) {
        coffeeExists(coffee.getId());
        checkCoffeeCount(coffee.getCount());
        checkExistingCoffeeSort(coffee.getSort(), coffee.getId());
    }

    public void checkCoffeeCount(long count) {
        if (count < 0 ) {
            throw new IncorrectDataException("Count can not be negative");
        }
    }

    public void checkCoffeeSort(String sort) {
        if (sort != null && coffeeDao.existsBySort(sort.toUpperCase())) {
            throw new IncorrectDataException("Sort must be unique");
        }
    }

    public void checkExistingCoffeeSort(String sort, int id) {
        if (sort != null && coffeeDao.existsBySortExcept(sort.toUpperCase(), id)) {
            throw new IncorrectDataException("Sort must be unique");
        }
    }

    public void coffeeExists(int id) {
        if (!coffeeDao.coffeeExistsById(id)) {
            throw new CoffeeNotFoundException();
        }
    }
}
