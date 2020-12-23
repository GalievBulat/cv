package Servlets;

import View.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/sign_out")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession(false).setAttribute("tc", null);
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
            }
            resp.sendRedirect("/cv/auth");
        }catch (RuntimeException e) {
            //req.setAttribute("errorMessage",e.getMessage());
            //resp.sendRedirect("cv/auth");
            Render render = new Render();
            Map<String, Object> root = new HashMap<>();
            root.put("errorMessage",e.getMessage());
            render.renderMap("auth.ftl",root,resp.getWriter());
        }
    }
}
