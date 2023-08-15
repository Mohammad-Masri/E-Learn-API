package com.elearn.app.elearnapi.config.filters;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elearn.app.elearnapi.utilities.PrintUtilities;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(1)
public class TransactionFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        PrintUtilities
                .println(String.format("Starting a transaction for req : %s %s", req.getMethod(), req.getRequestURI()));

        chain.doFilter(request, response);

    }

}
