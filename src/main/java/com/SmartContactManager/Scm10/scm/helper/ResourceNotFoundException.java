package com.SmartContactManager.Scm10.scm.helper;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(){
        super("Resource not found");
    }
    
}
