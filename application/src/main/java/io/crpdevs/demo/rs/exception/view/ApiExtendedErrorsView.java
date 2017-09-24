package io.crpdevs.demo.rs.exception.view;

import java.util.List;

import io.crpdevs.demo.rs.exception.error.ApiFieldError;
import io.crpdevs.demo.rs.exception.error.ApiGlobalError;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiExtendedErrorsView {

    private List<ApiFieldError> fieldErrors;
    private List<ApiGlobalError> globalErrors;
}
