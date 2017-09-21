package io.crpdevs.demo.rs.util.exception;

import io.crpdevs.demo.rs.exception.view.ApiExtendedErrorsView;
import io.crpdevs.demo.rs.exception.error.ApiFieldError;
import io.crpdevs.demo.rs.exception.error.ApiGlobalError;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ApiExtendedErrorsViewUtil {



    public static ApiExtendedErrorsView composeViewWith(BindingResult bindingResult) {
        return new ApiExtendedErrorsView(
            fromBindingFieldErrors(bindingResult.getFieldErrors()),
            fromBindingGlobaldErrors(bindingResult.getGlobalErrors())
        );
    }

    public static ApiExtendedErrorsView composeViewWith(Set<ConstraintViolation<?>> errors) {
        return new ApiExtendedErrorsView(
            fromBindingConstraintViolations(errors),
            null
        );
    }

    private static List<ApiFieldError> fromBindingConstraintViolations(Set<ConstraintViolation<?>> errors) {
        return errors
            .stream()
            .map(violation -> new ApiFieldError(
                violation.getPropertyPath().toString(),
                violation.getMessageTemplate(),
                violation.getInvalidValue(),
                violation.getMessage()
                )
            )
            .collect(toList());
    }

    private static List<ApiFieldError> fromBindingFieldErrors(List<FieldError> errors) {
        return errors
            .stream()
            .map(fieldError -> new ApiFieldError(
                fieldError.getField(),
                fieldError.getCode(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
                )
            )
            .collect(toList());
    }

    private static List<ApiGlobalError> fromBindingGlobaldErrors(List<ObjectError> errors) {
        return errors
            .stream()
            .map(globalError -> new ApiGlobalError(
                globalError.getCode())
            )
            .collect(toList());
    }
}
