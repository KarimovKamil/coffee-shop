package ru.kk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kk.models.Coffee;
import ru.kk.services.CoffeeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/coffee")
public class AdminController {
    private CoffeeService coffeeService;

    @Autowired
    public AdminController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity addCoffee(@RequestBody Coffee coffee) {
        int id = coffeeService.addCoffee(coffee);
        return ResponseEntity.created(URI.create("/admin/coffee/" + id)).build();
    }

    @GetMapping("/{id}")
    public Coffee getCoffee(@PathVariable int id) {
        return coffeeService.getCoffee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCoffee(@PathVariable int id, @RequestBody Coffee coffee) {
        coffee.setId(id);
        coffeeService.updateCoffee(coffee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCoffee(@PathVariable int id) {
        coffeeService.deleteCoffee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffees();
    }
}
