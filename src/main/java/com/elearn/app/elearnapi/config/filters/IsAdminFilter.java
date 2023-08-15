package com.elearn.app.elearnapi.config.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.elearn.app.elearnapi.utilities.PrintUtilities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IsAdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String id = (String) request.getAttribute("id");
        String role = (String) request.getAttribute("role");

        chain.doFilter(request, response);

    }
}
