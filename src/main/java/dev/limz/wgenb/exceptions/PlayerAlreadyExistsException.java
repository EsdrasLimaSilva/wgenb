package dev.limz.wgenb.exceptions;

public class PlayerAlreadyExistsException extends Exception{
    public PlayerAlreadyExistsException(){
        super("Player already exists");
    }
}
