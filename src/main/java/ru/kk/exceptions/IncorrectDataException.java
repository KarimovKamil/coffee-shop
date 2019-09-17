package ru.kk.exceptions;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String msg) {
        super(msg);
    }
}
