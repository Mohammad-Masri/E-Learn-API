package com.elearn.app.elearnapi.config.filters;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.elearn.app.elearnapi.errors.ErrorResponse;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.JWTUtilities;
import com.elearn.app.elearnapi.utilities.JsonUtilities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {

    private static final String[] EXCLUDED_URLS = {
            "/api/front/auth/login",
            "/api/front/auth/sign-up"
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
            // Skip filtering for excluded URLs
            chain.doFilter(request, response);
        } else {

            String JWTToken = JWTUtilities.getJWTFromRequest(request);

            boolean isTokenValid = JWTUtilities.isTokenValid(JWTToken);

            if (!isTokenValid) {

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

            chain.doFilter(request, response);
        }

    }
}
