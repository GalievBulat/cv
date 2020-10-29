package Servlets;

import DAO.DAO;
import DAO.UserTCDAOImpl;
import Model.UserTC;
import DAO.CommandHasntWorkedException;
import Service.PhotoReceiveHandler;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        try {
            DAO<UserTC> dao = new UserTCDAOImpl();
            long tc = (long) session.getAttribute("tc");
            Optional <UserTC> oUser = dao.find(tc);
            Render render= new Render();
            if (oUser.isPresent()){
                UserTC user = oUser.get();
                Map<String,Object> root = new HashMap<>();
                root.put("tc",user.getTc());
                root.put("name",user.getName());
                root.put("surname",user.getSurname());
                if (user.getAvatarPath()!=null){
                    root.put("avatar",user.getAvatarPath());
                } else root.put("avatar","avatar.png");
                render.renderMap("profile.ftl",root,resp.getWriter());
            }else {
                req.setAttribute("errorMessage","wrong password");
                resp.sendRedirect("/cv/auth");
            }
        } catch (IOException | RuntimeException | TemplateException throwables) {
            req.setAttribute("errorMessage",throwables.getMessage());
            resp.sendRedirect("/cv/auth");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part avatar =  req.getPart("avatar");
        PhotoReceiveHandler pR = new PhotoReceiveHandler();
        String path = pR.receiveAPhoto(avatar,getServletContext().getRealPath(""),(long)(req.getSession(false).getAttribute("tc")));
        UserPhotoAdding photoAdding = new UserPhotoAdding();
        photoAdding.addPhotoPathFromUser((long) req.getSession().getAttribute("tc"),path);
        resp.getWriter().write(path);
    }
}
