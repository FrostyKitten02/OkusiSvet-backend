package si.feri.okusisvet.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import si.feri.okusisvet.exceptions.NotAuthorizedException;
import si.feri.okusisvet.exceptions.UnauthorizedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SessionFilter extends OncePerRequestFilter {
    private static List<String> noAuthPaths = new ArrayList<>();
    private static List<String> noAuthOnlyPaths = new ArrayList<>();
    private static final String ALLOW_ALL = "**";

    static {
        noAuthPaths.add("recipes/list");
        noAuthPaths.add("recipes/"+ALLOW_ALL);
        noAuthOnlyPaths.add("/auth/login");
    }

    //TODO check if it works!
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO check if session cookie is set and valid, if not delete session cookie and set correct response code
        //deny all requests with session not set, except on certain endpoints!

        String requestUrl = request.getServletPath();
        boolean noAuthPath = checkIfPathInArray(noAuthPaths, requestUrl);
        if (noAuthPath) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean noAuthOnlyPath = checkIfPathInArray(noAuthOnlyPaths, requestUrl);
        Optional<Cookie> sessionCookie = Arrays.stream(request.getCookies())
                .filter(c->c.getName().equals(SecurityConstants.SESSION_COOKIE))
                .findFirst();
        if (noAuthOnlyPath && sessionCookie.isPresent()) {
            throw new NotAuthorizedException("Already logged in!");
        }

        if (sessionCookie.isPresent()) {
            filterChain.doFilter(request, response);
        } else {
            throw new UnauthorizedException("no authorization!");
        }
    }

    private static boolean checkIfPathInArray(List<String> paths, String requestPath) {
        String[] splitReqUrl = requestPath.split("/");

        for (String path : paths) {
            String[] splitPathUrl = path.split("/");

            if (splitReqUrl.length != splitPathUrl.length) {
                continue;
            }

            boolean samePath = false;
            for (int i = 0 ; i < splitPathUrl.length ; i++) {
                if (splitReqUrl[i].equals("**")) {
                    continue;
                } else if (splitReqUrl[i].equals(splitPathUrl[i])) {
                    samePath = true;
                } else {
                    samePath = false;
                }
            }

            if (samePath) {
                return true;
            }
        }

        return false;
    }

}
