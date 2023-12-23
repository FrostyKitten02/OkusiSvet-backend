package si.feri.okusisvet.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleUnhandledRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.warn("Unhandled server exception: " + ex.getMessage());
        log.error(ex.getLocalizedMessage(), ex);
        return ResponseEntity.status(status).body(new ExceptionResponse(status, "Unhandled server exception"));
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleItemNotFound(CustomRuntimeException ex) {
        return ex.buildResponseEntity();
    }
}