package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomRuntimeException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
