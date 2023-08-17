package com.elearn.app.elearnapi.config.filters;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean<TransactionFilter> transactionFilter() {
        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new TransactionFilter());
        registrationBean.setUrlPatterns(Arrays.asList("*"));
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter() {
        FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JWTFilter());
        registrationBean.setUrlPatterns(Arrays.asList("*"));
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<IsAdminFilter> isAdminFilter() {
        FilterRegistrationBean<IsAdminFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new IsAdminFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/dashboard/*"));
        registrationBean.setOrder(3);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<IsStudentFilter> isStudentFilter() {
        FilterRegistrationBean<IsStudentFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new IsStudentFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/front/*"));
        registrationBean.setOrder(3);

        return registrationBean;
    }

}
