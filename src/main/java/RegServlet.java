import DAO.CommandHasntWorkedException;
import Service.UserAddingHandler;
import Service.UserAddingHandlerToADB;
import Service.UserAddingHandlerToAFile;
import View.Render;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.naming.InvalidNameException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.Encoder;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/reg")
public class RegServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phoneNum = req.getParameter("phone_num");
        final String tc = req.getParameter("tc");
        /*try {
                MessageDigest.getInstance("SHA").digest(req.getParameter("password").getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        final String password = req.getParameter("password");
        final String birthDay = req.getParameter("birth_day");

        String[] userData = {name,surname,email,phoneNum,tc,password,birthDay};
        List<String> userDataList = Arrays.stream(userData).collect(Collectors.toList());
        UserAddingHandler addingHandler = new UserAddingHandlerToADB();
        try {
            addingHandler.addUser(userDataList);
            req.getSession().setAttribute("tc",tc);
            resp.sendRedirect("/cv/profile");
        }catch (IOException| SQLException  e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidNameException | CommandHasntWorkedException e) {
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
            render.renderMap("reg.ftl",root,resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}