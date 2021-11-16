package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles all the exceptions that might be thrown by the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * IllegalArgumentException handler
     *
     * @param exception IllegalArgumentException
     * @return Response containing the message of the exception and the appropriate HTTP code
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
