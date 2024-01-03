package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends CustomRuntimeException {
    public NotAuthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
