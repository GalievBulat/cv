package Servlets;

import Interfaces.UserAddingHandler;
import Service.UserOperatingHandlerDB;
import View.Render;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/reg")
public class RegServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        UserAddingHandler addingHandler = new UserOperatingHandlerDB();
        try {
            addingHandler.addUser(req);
            req.getSession().setAttribute("tc",Long.parseLong(req.getParameter("tc")));
            resp.sendRedirect("/cv/profile");
        }catch (IOException  e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch ( RuntimeException e) {
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
            render.renderMap("registration.ftl",root,resp.getWriter());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}