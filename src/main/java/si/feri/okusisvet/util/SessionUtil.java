package si.feri.okusisvet.util;

import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
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

}
