package dev.jonkursani.restapigr2.exceptions;

public class ConflictException extends IllegalArgumentException {
    public ConflictException(String message) {
        super(message);
    }
}