package com.tcv.onlinecatalog.exception;

public class NoStudentException extends Exception{
    public NoStudentException(){
        super("The student that you are looking for does not exist");
    }
}
