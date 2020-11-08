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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet("/update_comments")
public class CommentsUpdatingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = PostsServlet.limit;
        String currentPost= req.getReader().lines().collect(Collectors.joining());
        if (currentPost.equals(""))
            currentPost = "1";
        int startingPost = Integer.parseInt(currentPost);
        PostsOperatingHandler postsGettingHandler = new PostsOperatingHandler();
        List<Post> rootPosts= postsGettingHandler.getRootPosts();
        List<Post> resultList= new ArrayList<>();
        int endingPost = limit + startingPost;
        if (startingPost + limit>rootPosts.size())
            endingPost =rootPosts.size();
        for (int i = startingPost; i < endingPost; i++) {
            Post post = rootPosts.get(i);
            resultList.add(post);
        }
        resp.getWriter().write(new JSONConverter().postsListToJSON(resultList, new RussianLocalization()));
    }
}

