package com.techreturners.bookmanager.exception;



public class BookNotFoundException extends RuntimeException {
    String defaultMessage = " book is not found with id:";
    public BookNotFoundException(String message) {
        super(message);
    }
}

