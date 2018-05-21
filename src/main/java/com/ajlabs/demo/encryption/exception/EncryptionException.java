package com.ajlabs.demo.encryption.exception;

public class EncryptionException extends RuntimeException {

    public EncryptionException(String message, Throwable ex) {
        super(message, ex);
    }
}