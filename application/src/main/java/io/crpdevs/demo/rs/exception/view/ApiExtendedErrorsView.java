package io.crpdevs.demo.rs.exception.view;

import io.crpdevs.demo.rs.exception.error.ApiFieldError;
import io.crpdevs.demo.rs.exception.error.ApiGlobalError;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
public class ApiExtendedErrorsView {

    private List<ApiFieldError> fieldErrors;
    private List<ApiGlobalError> globalErrors;
}
