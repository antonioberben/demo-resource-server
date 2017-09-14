package io.crpdevs.demo.rs.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException  {
    private static final String NOT_FOUND_DEFAULT_MESSAGE = "Resource not found";

    private String resourceId;

    public ResourceNotFoundException(String resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public ResourceNotFoundException(String resourceId) {
        super(NOT_FOUND_DEFAULT_MESSAGE);
        this.resourceId = resourceId;
    }
}
