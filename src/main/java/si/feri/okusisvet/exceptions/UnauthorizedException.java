package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomRuntimeException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
