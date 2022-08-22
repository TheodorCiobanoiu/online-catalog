package com.tcv.onlinecatalog.exception;

public class NoSpecialtyException extends Exception{
    public NoSpecialtyException(){
        super("The specialty that you are looking for does not exist");
    }
}
