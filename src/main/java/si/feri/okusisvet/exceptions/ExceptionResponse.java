package si.feri.okusisvet.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private int status;
    private String error;
    private String message;

    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }

}