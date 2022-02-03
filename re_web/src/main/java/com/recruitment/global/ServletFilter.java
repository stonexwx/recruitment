package com.recruitment.global;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter")
public class ServletFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //转换ServletRequest，ServletResponse变为HttpServletRequest和HttpServletResponse
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //支持跨域，添加响应头
        httpResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "token,content-type");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //检测OPTIONS请求
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return ;
        }
        chain.doFilter(request, response);
    }
}
