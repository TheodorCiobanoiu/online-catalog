package com.tcv.onlinecatalog.exception;

public class NoGradeException extends Exception{
    public NoGradeException() {
        super("The grades list is empty");
    }
}
