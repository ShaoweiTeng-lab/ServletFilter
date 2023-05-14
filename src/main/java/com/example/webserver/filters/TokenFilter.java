package com.example.webserver.filters;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@WebFilter(filterName = "tokenFilter", urlPatterns = "/api/*")
public class TokenFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request , HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TokenFilter");
        String token = request.getHeader("Authorization");
        if (token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing.");
            return;
        }
        try {
            // 驗證 token 是否有效，如果無效，返回未授權的錯誤
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token.replace("Bearer ", ""));
        } catch (JwtException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid.");
            return;
        }
        chain.doFilter(request, response);
    }
}
