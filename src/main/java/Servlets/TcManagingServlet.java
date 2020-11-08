package Servlets;

import Service.TcManagerService;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/tc")
public class TcManagingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:ADD FILTER
        Render render =new Render();
        Map<String,Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        long tc = (long) session.getAttribute("tc");
        TcManagerService service= new TcManagerService();
        root.put("tc",tc);
        root.put("balance",service.getTcBalance(tc));
        render.renderMap("tcManager.ftl",root,resp.getWriter());
    }
}
