package Servlets;

import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
@WebServlet("/main")
public class MainPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Render render =new Render();
        try {
            render.renderMap("main_page.ftl",new HashMap<>(),resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
