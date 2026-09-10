package br.com.palpitou.exception;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
