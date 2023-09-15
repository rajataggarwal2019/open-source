package com.razik.market.controller;

import com.razik.market.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class SessionFilter implements Filter {
    private static final boolean isActive = true;
    private static final String cookieName = "SESSID";
    private static final String cookieValue = "jvq2mqqgl9NMp9R";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (isActive && !validate(httpServletRequest)) {
            servletResponse.getWriter().write("forbidden");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private static boolean validate(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getRequestURI().startsWith(Constant.INDEX_PATH)) {
            return true;
        }
        if (httpServletRequest.getCookies() == null) {
            return false;
        }
        Cookie[] cookieList = httpServletRequest.getCookies();
        for (Cookie cookie : cookieList) {
            if (cookieName.equals(cookie.getName())) {
                return cookieValue.equals(cookie.getValue());
            }
        }
        return false;
    }

}
