package com.kiran.service.exception;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String objectIdentifier;

    public NotFoundException(String objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
    }

    public String getObjectIdentifier() {
        return this.objectIdentifier;
    }

}
