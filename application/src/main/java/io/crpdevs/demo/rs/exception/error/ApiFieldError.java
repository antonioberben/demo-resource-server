package io.crpdevs.demo.rs.exception.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiFieldError {

    private String field;
    private String code;
    private Object rejectedValue;
    private String Message;
}
