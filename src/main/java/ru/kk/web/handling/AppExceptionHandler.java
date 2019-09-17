package ru.kk.web.handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kk.exceptions.CoffeeNotFoundException;
import ru.kk.exceptions.IncorrectDataException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = CoffeeNotFoundException.class)
    public ResponseEntity handleNotFoundException(CoffeeNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = IncorrectDataException.class)
    public ResponseEntity handleIncorrectCoffeeException(IncorrectDataException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
