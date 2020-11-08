package Servlets;

import DAO.UserPhotoAdding;
import Service.PhotoReceiveHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/avatar")
public class AvatarGettingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part avatar = req.getPart("avatar");
            PhotoReceiveHandler pR = new PhotoReceiveHandler();
            String path = pR.receiveAPhoto(avatar, getServletContext().getRealPath(""), (long) (req.getSession(false).getAttribute("tc")));
            UserPhotoAdding photoAdding = new UserPhotoAdding();
            photoAdding.addPhotoPathToUser((long) req.getSession().getAttribute("tc"), path);
            resp.getWriter().write(path);
        } catch (IOException | RuntimeException throwables) {
        req.setAttribute("errorMessage",throwables.getMessage());
        resp.sendRedirect("/cv/profile");
    }

    }
}
