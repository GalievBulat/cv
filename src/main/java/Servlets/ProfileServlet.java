package Servlets;

import Model.UserTC;
import Service.JSONConverter;
import Service.UserOperatingHandlerDB;
import View.Render;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
            UserOperatingHandlerDB searchingHandler = new UserOperatingHandlerDB();

            long tc = (long) session.getAttribute("tc");
            Optional <UserTC> oUser = searchingHandler.searchForUser(tc);
            Render render= new Render();
            if (oUser.isPresent()){
                UserTC user = oUser.get();
                Map<String,Object> root = new HashMap<>();
                root.put("tc",user.getTc());
                root.put("name",user.getName());
                root.put("surname",user.getSurname());
                if (user.getAvatarPath()!=null){
                    root.put("avatar",user.getAvatarPath());
                } else root.put("avatar","");
                if (req.getAttribute("errorMessage") != null)
                    root.put("errorMessage",req.getAttribute("errorMessage"));
                render.renderMap("profile.ftl",root,resp.getWriter());
            }else {
                req.setAttribute("errorMessage","wrong password");
                //resp.sendRedirect("/cv/auth");
                Map<String, Object> root = new HashMap<>();
                root.put("errorMessage","no user");
                render.renderMap("auth.ftl",root,resp.getWriter());
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            long tc = (long) session.getAttribute("tc");
            String json = req.getReader().lines().collect(Collectors.joining());
            JSONConverter converter = new JSONConverter();
            String[] strings = converter.convertJSON(json);
            String name = strings[0].equals("name") ? strings[1] : "";
            String surname = strings[2].equals("surname") ? strings[3] : "";
            UserOperatingHandlerDB handlerDB = new UserOperatingHandlerDB();
            UserTC userTC = handlerDB.searchForUser(tc).orElseThrow(IllegalArgumentException::new);
            userTC.setName(name);
            userTC.setSurname(surname);
            handlerDB.updateUser(userTC);
            resp.setStatus(200);
        }catch (RuntimeException e) {
            req.setAttribute("errorMessage","Exception: " +e.getMessage() );
            resp.setStatus(300);
            doGet(req,resp);
        }
    }
}
