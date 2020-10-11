import DAO.UserTCDAOImpl;
import Model.UserTC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reg")
public class RegServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserTC user = null;
        try {
            UserTCDAOImpl ud = new UserTCDAOImpl();
            user = ud.find(Long.parseLong(req.getParameter("tc"))).get();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.getWriter().write(String.valueOf(user));
    }
}
