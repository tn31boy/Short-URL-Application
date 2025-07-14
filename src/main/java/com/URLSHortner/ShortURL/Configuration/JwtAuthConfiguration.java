package com.URLSHortner.ShortURL.Configuration;

import com.URLSHortner.ShortURL.FilterChain.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAuthConfiguration {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilter()
    {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new JwtAuthFilter());
        filterRegistrationBean.addUrlPatterns("/genrate", "/update/*" , "/delete/*");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;


    }

}
