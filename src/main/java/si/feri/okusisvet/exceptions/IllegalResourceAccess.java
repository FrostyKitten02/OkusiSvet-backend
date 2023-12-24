package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalResourceAccess extends CustomRuntimeException {
    public IllegalResourceAccess(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
