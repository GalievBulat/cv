package Filters;

import DAO.UserTCRepositoryImpl;
import Interfaces.Repository;
import Model.UserTC;
import Service.IdEncoding;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/profile","/tc"})
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
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
            if (cookies!=null)
                for (Cookie cookie: cookies){
                    if (cookie.getName().equals("cookie")){
                        IdEncoding encoding = new IdEncoding();
                        Repository<UserTC> repository = new UserTCRepositoryImpl();
                        if (repository.get(encoding.decrypt(Double.parseDouble(cookie.getValue()))).isPresent()) {
                            ((HttpServletRequest) servletRequest).getSession(true).setAttribute("tc", encoding.decrypt(Double.parseDouble(cookie.getValue())));
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                        break;
                    }
                }
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
