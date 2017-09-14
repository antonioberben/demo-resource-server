package io.crpdevs.demo.rs.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiFieldError {

    private String field;
    private String code;
    private Object rejectedValue;
    private String Message;
}
