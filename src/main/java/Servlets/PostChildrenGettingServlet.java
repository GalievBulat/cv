package Servlets;

import Model.Post;
import Service.JSONConverter;
import Service.PostsOperatingHandler;
import Service.RussianLocalization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/get_comments")
public class PostChildrenGettingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getReader().lines().collect(Collectors.joining()));
        PostsOperatingHandler handler = new PostsOperatingHandler();
        Post post = handler.get(postId);
        resp.getWriter().write(new JSONConverter().postsListToJSON(handler.getChildren(post), new RussianLocalization()));
        resp.setStatus(200);
    }


}
