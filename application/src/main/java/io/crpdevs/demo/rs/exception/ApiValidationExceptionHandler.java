package io.crpdevs.demo.rs.exception;

import io.crpdevs.demo.rs.exception.view.ApiExtendedErrorsView;
import io.crpdevs.demo.rs.exception.view.ApiSimpleErrorsView;
import io.crpdevs.demo.rs.util.exception.ApiExtendedErrorsViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;


@Slf4j
@ControllerAdvice
public class ApiValidationExceptionHandler {


    public static final String HANDLED_ERROR_PREFIX = "HandledError;";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiSimpleErrorsView> resourceNotFound(ResourceNotFoundException ex) {
        log.warn(HANDLED_ERROR_PREFIX + HttpStatus.NOT_FOUND + ";" + HttpStatus.NOT_FOUND.getReasonPhrase() + ";" + ex.getMessage());
        ApiSimpleErrorsView response = new ApiSimpleErrorsView();
        response.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExtendedErrorsView> invalidRepresentationInput(MethodArgumentNotValidException ex) {
        log.warn(HANDLED_ERROR_PREFIX + HttpStatus.BAD_REQUEST + ";" + HttpStatus.BAD_REQUEST.getReasonPhrase() +
            ";" + ex.getMessage());
        ApiExtendedErrorsView apiErrorsView = ApiExtendedErrorsViewUtil.composeViewWith(ex.getBindingResult());
        return new ResponseEntity<>(apiErrorsView, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiExtendedErrorsView> invalidEntityInput(ConstraintViolationException ex) {
        log.error(
            HANDLED_ERROR_PREFIX + HttpStatus.UNPROCESSABLE_ENTITY + ";" +
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase() + ";Constraint Violations directly on DB");
        ApiExtendedErrorsView apiErrorsView = ApiExtendedErrorsViewUtil.composeViewWith(ex.getConstraintViolations());
        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
