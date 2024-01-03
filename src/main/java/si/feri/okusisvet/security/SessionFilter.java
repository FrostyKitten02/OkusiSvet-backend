package si.feri.okusisvet.security;

import com.google.firebase.ErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.web.filter.OncePerRequestFilter;
import si.feri.okusisvet.exceptions.NotAuthorizedException;
import si.feri.okusisvet.exceptions.UnauthorizedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SessionFilter extends OncePerRequestFilter {
    private static final List<String> noAuthPaths = new ArrayList<>();
    private static final List<String> noAuthOnlyPaths = new ArrayList<>();
    private static final String ALLOW_ALL = "**";

    static {
        noAuthPaths.add("/recipes/list");
        noAuthPaths.add("/recipes/"+ALLOW_ALL);
        noAuthPaths.add("/units/type/"+ALLOW_ALL);
        noAuthPaths.add("/ingredient-types/"+ALLOW_ALL);

        noAuthPaths.add("/swagger-ui/"+ALLOW_ALL);
        noAuthPaths.add("/v3/api-docs");
        noAuthPaths.add("/v3/api-docs.yaml");
        noAuthPaths.add("/v3/api-docs/swagger-config");


        noAuthOnlyPaths.add("/auth/login");
    }
    private final FirebaseAuth firebaseAuth;

    //TODO check if it works!
    public SessionFilter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<Cookie> sessionCookie;
        if (request.getCookies() == null || request.getCookies().length == 0) {
            sessionCookie = Optional.empty();
        } else {
            sessionCookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals(SecurityConstants.SESSION_COOKIE))
                    .findFirst();
        }

        String requestUrl = request.getServletPath();
        boolean noAuthPath = checkIfPathInArray(noAuthPaths, requestUrl);
        if (noAuthPath) {
            //even tho auth is not required we still check if user is logged in and set our context
            if (sessionCookie.isPresent()) {
                try {
                    checkSessionAndAddUserToContext(request, response, sessionCookie.get());
                } catch (Exception e) {
                    log.warn("This error is expected, should not be of any concern!");
                    log.warn(e.getLocalizedMessage(), e);
                }
            }

            filterChain.doFilter(request, response);
            return;
        }

        boolean noAuthOnlyPath = checkIfPathInArray(noAuthOnlyPaths, requestUrl);

        if (noAuthOnlyPath && sessionCookie.isPresent()) {
            throw new NotAuthorizedException("Already logged in!");
        } else if (noAuthOnlyPath) {
            filterChain.doFilter(request, response);
            return;
        }

        if (sessionCookie.isPresent()) {
            checkSessionAndAddUserToContext(request, response, sessionCookie.get());
            filterChain.doFilter(request, response);
        } else {
            throw new UnauthorizedException("no authorization!");
        }
    }

    private void checkSessionAndAddUserToContext(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) {
        try {
            FirebaseToken token = firebaseAuth.verifySessionCookie(sessionCookie.getValue(), true);
            request.setAttribute(SecurityConstants.FIREBASE_TOKEN_ATTRIBUTE, token);
        } catch (FirebaseAuthException e) {
            if (e.getErrorCode() == ErrorCode.CANCELLED || e.getErrorCode() == ErrorCode.NOT_FOUND) {
                Cookie removeCookie = new Cookie(SecurityConstants.SESSION_COOKIE, null);
                response.addCookie(removeCookie);
            }
            throw new UnauthorizedException("Failed authentication with firebase", e);
        }
    }


    private static boolean checkIfPathInArray(List<String> paths, String requestPath) {
        String[] splitReqUrl = requestPath.split("/");

        for (String path : paths) {
            String[] splitPathUrl = path.split("/");

            if (!splitPathUrl[splitPathUrl.length-1].equals(ALLOW_ALL) && splitReqUrl.length != splitPathUrl.length) {
                continue;
            }

            boolean samePath = false;
            for (int i = 0 ; i < splitPathUrl.length ; i++) {
                if (splitPathUrl[i].equals(ALLOW_ALL)) {
                    //set to true??
                    samePath = true;
                } else if (splitReqUrl[i].equals(splitPathUrl[i])) {
                    samePath = true;
                } else {
                    samePath = false;
                }

                if (!samePath) {
                    break;
                }
            }


            if (samePath) {
                return true;
            }
        }

        return false;
    }

}
