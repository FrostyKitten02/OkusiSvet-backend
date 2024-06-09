package si.feri.okusisvet.controller;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.security.SecurityConstants;
import si.feri.okusisvet.util.SessionUtil;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final FirebaseAuth firebaseAuth;
    @Value("${session.cookie.path}")
    private String sessionCookiePath;
    public record FirebaseTokenLogin(String idToken){}

    //todo move logic to service class
    @PostMapping("login")
    public void login(@RequestBody FirebaseTokenLogin request, HttpServletResponse response) throws FirebaseAuthException {
        String idToken = request.idToken();
        long expiresIn = TimeUnit.DAYS.toMillis(1);
        SessionCookieOptions options = SessionCookieOptions.builder()
                .setExpiresIn(expiresIn)
                .build();
        // Create the session cookie. This will also verify the ID token in the process.
        // The session cookie will have the same claims as the ID token.
        String sessionCookie = firebaseAuth.createSessionCookie(idToken, options);
        Cookie cookie = new Cookie(SecurityConstants.SESSION_COOKIE, sessionCookie);
        cookie.setMaxAge((int)expiresIn/1000);
        cookie.setPath(sessionCookiePath);
        response.addCookie(cookie);
    }

    @GetMapping("logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(SecurityConstants.SESSION_COOKIE, null);
        cookie.setMaxAge(0);
        cookie.setPath(sessionCookiePath);
        response.addCookie(cookie);
    }

    @GetMapping("check")
    public String currentUser(HttpServletRequest request) {
        return SessionUtil.getUserId(request);
    }
}
