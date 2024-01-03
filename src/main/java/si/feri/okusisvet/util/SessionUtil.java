package si.feri.okusisvet.util;

import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import si.feri.okusisvet.exceptions.BadRequestException;
import si.feri.okusisvet.security.SecurityConstants;


public class SessionUtil {

    private SessionUtil() {}
    public static String getUserId(@NotNull HttpServletRequest req) {
        FirebaseToken firebaseToken = (FirebaseToken) req.getAttribute(SecurityConstants.FIREBASE_TOKEN_ATTRIBUTE);
        if (firebaseToken == null) {
            return null;
        }

        return firebaseToken.getUid();
    }

    public static String getUserIdStrict(@NotNull HttpServletRequest req) {
        String userId = getUserId(req);
        if (userId == null) {
            throw new BadRequestException("User not logged in");
        }

        return userId;
    }

}
