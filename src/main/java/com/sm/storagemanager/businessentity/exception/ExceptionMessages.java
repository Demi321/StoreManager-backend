package com.sm.storagemanager.businessentity.exception;

public enum ExceptionMessages {
    
    ENTITY_ALREADY_EXIST("El nombre del negocio ingresado ya está registrada.");

    private String message;

    private ExceptionMessages(String message){
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }
}
