package com.tcv.onlinecatalog.exception;

public class NoProfessorException extends Exception{
    public NoProfessorException(){
        super("The professor that you are looking for does not exist");
    }
}
