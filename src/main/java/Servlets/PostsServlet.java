package Servlets;

import Model.Post;
import Service.JSONConverter;
import Service.PostCategoriesService;
import Service.PostsOperatingHandler;
import Service.RussianLocalization;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/forum")

public class PostsServlet extends HttpServlet{
    static final int limit = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostCategoriesService service = new PostCategoriesService();
        HttpSession session =req.getSession(false);
        long tc = 0L;
        if (session!=null  && session.getAttribute("tc")!=null){
            tc = (long)session.getAttribute("tc");
        }
        Render render = new Render();
        List<String> categories = service.getAll();
        Map<String,Object> root =new HashMap<>();
        root.put("author_id",String.valueOf(tc));
        root.put("currentPost", 1);
        root.put("categories",new RussianLocalization().localizeCategories(categories));
        if (req.getAttribute("errorMessage") != null)
            root.put("errorMessage",req.getAttribute("errorMessage"));
        render.renderMap("forum.ftl",root,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader iS = req.getReader();
            JSONConverter jConv = new JSONConverter();
            String[] strings = jConv.convertJSON(iS.readLine());
            long parentId = strings[0].equals("parent_id") ? Long.parseLong(strings[1]) : 0;
            long authorId = strings[2].equals("author_id") ? Long.parseLong(strings[3]) : 0;
            String message = strings[4].equals("message") ? strings[5] : "";
            List<Long> categories = new ArrayList<>();
            if (strings.length >= 8) {
                categories.add(strings[6].equals("categories") ? Long.parseLong(strings[7]) : 0);
                if (strings.length >= 9)
                    for (int i = 8; i < strings.length; i++) {
                        categories.add(Long.parseLong(strings[i]));
                    }
            } else {
                categories.add(0L);
            }
            if (authorId < 0 || message == null) {
                throw new IllegalArgumentException();
            }
            Post post = new Post.Builder()
                    .authorId(authorId)
                    .message(message)
                    .parentId(parentId)
                    .time(java.sql.Time.valueOf(LocalTime.now()))
                    .date(java.sql.Date.valueOf(LocalDate.now()))
                    .build();
            PostsOperatingHandler handler = new PostsOperatingHandler();
            handler.add(post, categories);
            resp.setStatus(200);
        } catch (RuntimeException e) {
            req.setAttribute("errorMessage",e.getMessage());
            doGet(req,resp);
        }
    }
}