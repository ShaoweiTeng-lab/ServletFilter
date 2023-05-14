package com.example.webserver.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ZeroFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request , HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("ZeroFilter");
        int convertNum1= Integer.parseInt(request.getParameter("num1"));
        int convertNum2=Integer.parseInt(request.getParameter("num2"));
        if(convertNum1==0||convertNum2==0){response.setContentType("text/plain:charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("不可輸入為0的數");
        }else {
            chain.doFilter(request, response);
        }
    }

}
