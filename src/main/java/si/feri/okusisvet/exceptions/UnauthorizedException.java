package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    public UnauthorizedException(String message) {
        super(message, httpStatus);
    }

    public UnauthorizedException(String messsage, Throwable throwable) {
        super(messsage, throwable, httpStatus);
    }
}
