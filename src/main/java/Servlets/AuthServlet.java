package Servlets;

import Model.UserTC;
import Interfaces.AuthHandler;
import Service.UserOperatingHandlerDB;
import Service.IdEncoding;
import Service.TextEncoding;
import View.Render;
import freemarker.template.TemplateException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        TextEncoding textEncoding = new TextEncoding();

        final long tc = Long.parseLong(req.getParameter("tc"));
        final String password = textEncoding.encodeText(req.getParameter("password"));
        final boolean remember =  req.getParameter("remember_me").equals("on") ;
        AuthHandler handler = new UserOperatingHandlerDB();
        try {
            UserTC user = handler.authoriseByTC(tc,password).orElseThrow(() -> new InvalidKeyException("wrong password"));
            req.getSession().setAttribute("tc",user.getTc());
            if (remember) {
                IdEncoding encoding = new IdEncoding();
                Cookie cookie = new Cookie("cookie", String.valueOf(encoding.encrypt(user.getTc())));
                cookie.setMaxAge(-1);
                resp.addCookie(cookie);
            }
            resp.sendRedirect("/cv/profile");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (InvalidKeyException | RuntimeException e) {
            req.setAttribute("errorMessage",e.getMessage());
            doGet(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            Render render = new Render();
            Map<String, Object> root = new HashMap<>();
            if (req.getAttribute("errorMessage") != null) {
                root.put("errorMessage",req.getAttribute("errorMessage"));
            }else {
                root = Collections.emptyMap();
            }
            render.renderMap("auth.ftl",root,resp.getWriter());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
