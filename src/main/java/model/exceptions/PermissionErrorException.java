package model.exceptions;

public class PermissionErrorException extends RuntimeException {
    public PermissionErrorException(String message){
        super(message);
    }
}


