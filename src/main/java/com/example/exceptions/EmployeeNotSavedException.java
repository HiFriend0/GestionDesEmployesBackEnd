package com.example.exceptions;

public class EmployeeNotSavedException extends Exception {

    @Override
    public String getMessage() {
        return "Not possible to insert employee in database";
    }
}
