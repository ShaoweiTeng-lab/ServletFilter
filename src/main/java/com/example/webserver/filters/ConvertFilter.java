package com.example.webserver.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ConvertFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request , HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("ConvertFilter");
        String num1=request.getParameter("num1");
        String num2=request.getParameter("num2");
        try {
            Integer.parseInt(request.getParameter("num1"));
            Integer.parseInt(request.getParameter("num2"));
            chain.doFilter(request, response );
        }
        catch (Exception e) {
            response.setContentType("text/plain:charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("請輸入整數");
        }


    }
}
