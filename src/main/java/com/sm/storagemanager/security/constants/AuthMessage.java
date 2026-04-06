package com.sm.storagemanager.security.constants;

public enum AuthMessage {
    
    USER_NOT_FOUND("Usuario no encontrado: ");

    private String message;

    AuthMessage(String message){
        this.message=message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
