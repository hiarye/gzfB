package hnpbc.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");

        // response.setHeader("Access-Control-Allow-Origin",reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8081");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, authorization, Accept");

        // 用户的请求url为登录页面则视为正常访问
        if (url.endsWith("login") || url.endsWith("js") || url.endsWith("css")|| url.endsWith("gif")|| url.endsWith("jpg")) {
            filterChain.doFilter(request, response);
            return;
        }
        //如果session为空，则说明用户没有登录，将当前网页重定向到登录页面
        if(null == username || ((String) username).length() ==0){
            request.getRequestDispatcher("/nologin").forward(request, response);
//            response.sendRedirect("../login.html");
            filterChain.doFilter(request, response);
            return;
        }else{  // 正常访问
            filterChain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {}
}
