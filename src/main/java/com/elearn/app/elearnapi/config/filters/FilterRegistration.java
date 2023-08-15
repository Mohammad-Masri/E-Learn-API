package com.elearn.app.elearnapi.config.filters;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean<TransactionFilter> loggingFilter() {
        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new TransactionFilter());
        registrationBean.setUrlPatterns(Arrays.asList("*"));
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
