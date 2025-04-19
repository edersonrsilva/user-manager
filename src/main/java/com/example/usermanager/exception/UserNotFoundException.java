package com.example.usermanager.exception;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
    
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
    
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}