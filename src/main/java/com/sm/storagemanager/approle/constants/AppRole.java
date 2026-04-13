package com.sm.storagemanager.approle.constants;

 

public enum AppRole {
    
    ADMIN("ADMINISTRADOR"),
    USER("USER");


    private String value;

    AppRole(String value){
        this.value=value;
    }


    public String getValue(){
        return this.value;
    }


}
