package com.sm.storagemanager.businessentity.exception;

public class BusinessEntityAlreadyExistsException extends RuntimeException {

    public BusinessEntityAlreadyExistsException(String taxId) {
        super(ExceptionMessages.ENTITY_ALREADY_EXIST.getMessage().concat(taxId));
    }

    public BusinessEntityAlreadyExistsException(String taxId, Throwable cause) {
        super(ExceptionMessages.ENTITY_ALREADY_EXIST.getMessage().concat(taxId), cause);
    }
}
