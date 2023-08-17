package com.elearn.app.elearnapi.config.filters;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.elearn.app.elearnapi.errors.ErrorResponse;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.JWTUtilities;
import com.elearn.app.elearnapi.utilities.JsonUtilities;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {

    private static final String[] EXCLUDED_URLS = {
            "/api/front/auth/login",
            "/api/front/auth/sign-up",
            "/api/dashboard/auth/login",
            "/api/swagger-ui",
            "/api/v3/api-docs"
    };

    // Add the URLs for APIs where authentication is optional
    private static final String[] OPTIONAL_AUTH_URLS = {
            "/api/front/topics",
            "/api/front/courses",
    };

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String requestURI = request.getRequestURI();

        // Check if the requested URL is in the exclusion list
        boolean exclude = false;
        for (String excludedUrl : EXCLUDED_URLS) {
            if (requestURI.startsWith(excludedUrl)) {
                exclude = true;
                break;
            }
        }

        if (exclude) {
            request.setAttribute("isGuest", true);
            // Skip filtering for excluded URLs
            chain.doFilter(request, response);
        } else {

            String JWTToken = JWTUtilities.getJWTFromRequest(request);

            boolean isTokenValid = JWTUtilities.isTokenValid(JWTToken);

            // Check if the requested URL is in the optional auth list
            boolean optionalAuth = false;
            for (String optionalUrl : OPTIONAL_AUTH_URLS) {
                if (requestURI.startsWith(optionalUrl)) {
                    optionalAuth = true;
                    break;
                }
            }

            if (!isTokenValid && !optionalAuth) {

                try {
                    throw new HTTPServerError(HttpStatus.UNAUTHORIZED, "Token is invalid or expired");
                } catch (HTTPServerError e) {
                    ErrorResponse errorResponse = new ErrorResponse(e.getStatusCode(), e.getMessage());
                    response.setContentType("application/json");
                    response.setStatus(e.getStatusCode());
                    response.getWriter().write(JsonUtilities.convertToJson(errorResponse));
                    return;
                }

            }

            if (isTokenValid) {
                Claims claims = JWTUtilities.getClaimsFromToken(JWTToken);
                request.setAttribute("id", claims.get("id"));
                request.setAttribute("role", claims.get("role"));
                request.setAttribute("isGuest", false);
            } else {
                request.setAttribute("isGuest", true);
            }

            chain.doFilter(request, response);
        }

    }
}
