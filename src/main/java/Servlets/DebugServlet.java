package Servlets;

import DAO.CommandHasntWorkedException;
import DAO.TimetableRepositoryImpl;
import DAO.UserTCDAOImpl;
import DAO.UserTCDAOMapper;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Render render =new Render();
        try {
            render.renderMap("job.ftl",new HashMap<>(),resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
