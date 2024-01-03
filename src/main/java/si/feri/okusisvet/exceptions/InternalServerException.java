package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerException extends CustomRuntimeException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String defaultMsg = "Internal server error";

    public InternalServerException() {
        super(defaultMsg, status);
    }
    public InternalServerException(String message) {
        super(message, status);
    }
}
