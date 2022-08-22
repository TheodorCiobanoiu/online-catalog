package com.tcv.onlinecatalog.exception;

public class NoCourseException extends Exception{
    public NoCourseException(){
        super("The course that you are looking for does not exist");
    }
}
