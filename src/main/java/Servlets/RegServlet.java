package Servlets;

import Service.TextEncoding;
import Service.UserAddingHandler;
import Service.UserAddingHandlerToADB;
import View.Render;
import freemarker.template.TemplateException;

import javax.naming.InvalidNameException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RegServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        TextEncoding textEncoding = new TextEncoding();
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phoneNum = req.getParameter("phone_num");
        final Long tc = Long.parseLong(req.getParameter("tc"));
        final String password = textEncoding.encodeText(req.getParameter("password"));
        final String birthDay = req.getParameter("birth_day");

        String[] userData = {name,surname,email,phoneNum,tc.toString(),password,birthDay};
        List<String> userDataList = Arrays.stream(userData).collect(Collectors.toList());
        UserAddingHandler addingHandler = new UserAddingHandlerToADB();
        try {
            addingHandler.addUser(userDataList);
            req.getSession().setAttribute("tc",tc);
            resp.sendRedirect("/cv/profile");
        }catch (IOException  e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidNameException | RuntimeException e) {
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
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}