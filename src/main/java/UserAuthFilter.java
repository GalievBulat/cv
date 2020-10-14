import com.sun.net.httpserver.HttpExchange;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class UserAuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session =((HttpServletRequest)servletRequest).getSession(false);
        if (session!=null  && session.getAttribute("tc")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            try {
                ((HttpServletResponse)servletResponse).sendRedirect("/cv/auth");
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
