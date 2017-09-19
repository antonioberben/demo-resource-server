package io.crpdevs.demo.rs.exception;

public class ResourceNotFoundException extends RuntimeException  {
    private static final String NOT_FOUND_DEFAULT_MESSAGE = "Resource not found";

    public ResourceNotFoundException(String resourceId) {
        super(NOT_FOUND_DEFAULT_MESSAGE);
    }
}
