package com.sicimike.mayserver.filter;

import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.exception.AppKeyInvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author sicimike
 * @create 2018-12-13 15:19
 */
@Component
public class AuthFilter implements Filter {

    @Value("${weixin.appKey}")
    private String weixinAppKey;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String appKey = request.getHeader("appKey");
        if (!weixinAppKey.equals(appKey)) {
            throw new AppKeyInvalidException(EnumResponseCode.APPKEY_ERROR.getMessage());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
