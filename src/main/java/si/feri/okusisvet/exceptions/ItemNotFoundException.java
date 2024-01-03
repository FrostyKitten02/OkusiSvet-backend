package si.feri.okusisvet.exceptions;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends CustomRuntimeException {
    public ItemNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}