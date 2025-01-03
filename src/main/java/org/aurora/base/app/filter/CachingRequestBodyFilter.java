package org.aurora.base.app.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

public class CachingRequestBodyFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(req);

        chain.doFilter(wrapper, res);
    }
}
