package ru.jugsev.ddtwithjunit.controller.exception;

/**
 * Created by lconnected on 11/10/2018.
 */
public class CensorshipException extends RuntimeException {
    public CensorshipException() {
        super();
    }

    public CensorshipException(String message) {
        super(message);
    }

    public CensorshipException(String message, Throwable cause) {
        super(message, cause);
    }

    public CensorshipException(Throwable cause) {
        super(cause);
    }
}
