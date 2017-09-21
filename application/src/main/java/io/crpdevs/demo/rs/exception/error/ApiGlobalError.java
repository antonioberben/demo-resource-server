package io.crpdevs.demo.rs.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiGlobalError {

    private String code;
}
