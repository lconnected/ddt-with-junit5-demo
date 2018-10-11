package ru.jugsev.ddtwithjunit.models;

import java.util.Date;

/**
 * The error message object
 * Created by lconnected on 10/10/2018.
 */
public class ErrorMessage {

    public String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
