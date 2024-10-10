package org.example.controller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");
        if(timezone != null && !timezone.isEmpty()) {
            if(timezone.contains(" ")) {
                timezone = timezone.replace(" ","+");
                if (timezone.matches("UTC[+-]\\d{1,2}")) {
                    chain.doFilter(req, res);
                }
                else {
                    res.sendError(400, "Invalid timezone UTC");
                }
            }
            else if(timezone.equals("UTC")) {
                chain.doFilter(req, res);
            }
        }
        else {
            chain.doFilter(req, res);
        }
    }
}
