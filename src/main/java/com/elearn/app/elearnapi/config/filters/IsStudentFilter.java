package com.elearn.app.elearnapi.config.filters;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.elearn.app.elearnapi.config.constants.UserRole;
import com.elearn.app.elearnapi.errors.ErrorResponse;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.JsonUtilities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IsStudentFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String role = (String) request.getAttribute("role");
        Boolean isGuest = (Boolean) request.getAttribute("isGuest");

        if (!isGuest) {

            if (role == null || !role.equals(UserRole.STUDENT.toString())) {
                try {
                    throw new HTTPServerError(HttpStatus.UNAUTHORIZED, "this action is not allowed");
                } catch (HTTPServerError e) {
                    ErrorResponse errorResponse = new ErrorResponse(e.getStatusCode(),
                            e.getMessage());
                    response.setContentType("application/json");
                    response.setStatus(e.getStatusCode());
                    response.getWriter().write(JsonUtilities.convertToJson(errorResponse));
                    return;
                }
            }

        }

        chain.doFilter(request, response);

    }
}
