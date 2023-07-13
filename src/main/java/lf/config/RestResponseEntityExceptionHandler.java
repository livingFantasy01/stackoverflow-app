package lf.config;

import lf.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleBusinessException(BusinessException businessException) {

        return new ResponseEntity<>(businessException, HttpStatus.BAD_REQUEST);
    }
}