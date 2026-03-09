package dev.jonkursani.restapigr2.exceptions.book;

import dev.jonkursani.restapigr2.exceptions.ResourceNotFoundException;

public class BookNotFoundException extends ResourceNotFoundException {
    public BookNotFoundException(long id) {
        super("Book with id " + id + " not found");
    }
}