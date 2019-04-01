package controller.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter extends AbstractFilter {
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";
    
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        filterChain.doFilter(request, response);
    }


}

