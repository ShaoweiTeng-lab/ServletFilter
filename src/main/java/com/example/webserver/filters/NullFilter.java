package com.example.webserver.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class NullFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request , HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("NullFilter");
        String num1=request.getParameter("num1");
        String num2=request.getParameter("num2");
        if (num1 != null && num2 != null && !num1.isEmpty() && !num1.isEmpty() && !num2.isEmpty()) {
            chain.doFilter(request, response );
        }
        else {
            response.setContentType("text/plain:charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("不能為空");
        }
    }
}
