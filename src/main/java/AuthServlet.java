import DAO.CommandHasntWorkedException;
import Model.UserTC;
import Service.AuthHandler;
import Service.AuthHandlerToADB;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        final long tc = Long.parseLong(req.getParameter("tc"));
        final String password = req.getParameter("password");
        AuthHandler handler = new AuthHandlerToADB();
        try {
            UserTC user = handler.authoriseByTC(tc,password).orElseThrow(() -> new InvalidKeyException("wrong password"));
            req.getSession().setAttribute("tc",user.getTc());
            resp.sendRedirect("/cv/profile");
        } catch (IOException| SQLException  e) {
            throw new RuntimeException(e.getMessage(),e);
        } catch (InvalidKeyException | CommandHasntWorkedException e) {
            req.setAttribute("errorMessage",e.getMessage());
            doGet(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Kakad\\Documents\\cv\\src\\main\\resources\\templatetes"));
            cfg.setDefaultEncoding("UTF-8");
            Template temp = cfg.getTemplate("auth.ftl");
            Map<String, Object> root = new HashMap<>();
            if (req.getAttribute("errorMessage") != null) {
                root.put("errorMessage",req.getAttribute("errorMessage"));
            }else {
                root = Collections.emptyMap();
            }
            temp.process(root, resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
