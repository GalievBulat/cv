import Service.UserAddingHandler;
import Service.UserAddingHandlerToAFile;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/regMe")
public class RegServlet2 extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phoneNum = req.getParameter("phone_num");
        final String tc = req.getParameter("tc");
        final String password = req.getParameter("password");
        final String birthDay = req.getParameter("birth_day");

        String[] userData = {name,surname,email,phoneNum,tc,password,birthDay};
        List<String> userDataList = Arrays.stream(userData).collect(Collectors.toList());
        UserAddingHandler addingHandler = new UserAddingHandlerToAFile();
        boolean result = addingHandler.addUser(userDataList);
        if (result){
            try {
                req.getSession().setAttribute("name",name);
                resp.sendRedirect("/cv/reg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeAddingResult(UserAddingHandler addingHandler, List<String> userDataList, ServletResponse resp){
        try {
            resp.getWriter().write(Boolean.toString(addingHandler.addUser(userDataList)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

        try {
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Kakad\\Documents\\webb\\src\\main\\webapp\\WEB-INF\\template"));
            cfg.setDefaultEncoding("UTF-8");
            Template temp = cfg.getTemplate("reg2.ftl");
            Map<String, Object> root = Collections.emptyMap();
            temp.process(root, resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}