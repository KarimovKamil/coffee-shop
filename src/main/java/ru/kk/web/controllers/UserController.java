package ru.kk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kk.models.Order;
import ru.kk.services.CoffeeService;

@Controller
public class UserController {
    private CoffeeService coffeeService;

    @Autowired
    public UserController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping({"/", "/coffee"})
    public ModelAndView getShowPage() {
        ModelAndView view = new ModelAndView("index.html");
        view.addObject("coffees", coffeeService.getAllCoffees());
        return view;
    }

    @PostMapping("/coffee/{id}")
    @ResponseBody
    public Long buyCoffee(@PathVariable int id, @RequestParam int count) {
        return coffeeService.buyCoffee(id, count).getOrderId();
    }
}
