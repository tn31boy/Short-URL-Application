package com.URLSHortner.ShortURL.FilterChain;

import com.URLSHortner.ShortURL.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils ;


    @Override
    public void doFilterInternal(HttpServletRequest  request , HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException
    {
        System.out.println("Request URI: " + request.getRequestURI());

        String authHeader=request.getHeader("Authorization");
        System.out.println(authHeader +" "+1);
        if(authHeader==null || authHeader.startsWith("Barear")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no token Present");
            return ;
        }

        String token =authHeader.substring(7);
        System.out.println(token +" "+2);

        if(token.isEmpty()  || token.split("\\.").length!=3){
            System.out.println(token.split("\\.").length);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Not a valid Token");
            return ;
        }
        System.out.println(token +" "+3);

        try {

            String subject=jwtUtils.isTokenValid(token);
            if (subject == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token Expired");
                return;
            }
        }catch(Exception e)
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"invalid token");
            return ;
        }

        filterChain.doFilter(request,response);

    }


}
