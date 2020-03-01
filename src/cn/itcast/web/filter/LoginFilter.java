package cn.itcast.web.filter;

import cn.itcast.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    // 跳过该过滤器的 URI 包含的字符串
    private static String[] ignoreURI=new String[]{"login","css/","js/","fonts/","checkCode"};

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;


        String uri = req.getRequestURI();

        if(ignore(uri)){
            chain.doFilter(request, response);
        }else{
            User user= (User) req.getSession().getAttribute("user");
            if(user==null){
                response.getWriter().write("请先登录！");
                resp.setHeader("refresh","2;URL="+req.getContextPath()+"/login.jsp");
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    // 判断该uri是否可以跳过这个过滤器
    private boolean ignore(String uri) {
        for(String ignore:ignoreURI){
            if(uri.contains(ignore)){
                return true;
            }
        }
        return false;
    }

    public void destroy() {
    }

}
