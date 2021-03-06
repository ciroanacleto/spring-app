package com.example.spring.api.cors;

import com.example.spring.api.config.property.SpringApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    private SpringApiProperty property;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Controll-Allow-Origin", property.getOriginPermitida());
        response.setHeader("Access-Controll-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod()) && property.getOriginPermitida().equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Controll-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Controll-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Controll-Max-Age", "3600");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, response);
        }

    }

    @Override
    public void destroy() {

    }
}
