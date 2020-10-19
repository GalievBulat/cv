import DAO.DAO;
import DAO.UserTCDAOImpl;
import Model.UserTC;
import DAO.CommandHasntWorkedException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        try {
            DAO<UserTC> dao = new UserTCDAOImpl();
            long tc = (long) session.getAttribute("tc");
            Optional <UserTC> oUser = dao.find(tc);
            if (oUser.isPresent()){
                UserTC user = oUser.get();
                resp.getWriter().write(user.toString());
            }else {
                req.setAttribute("errorMessage","wrong password");
                resp.sendRedirect("/cv/auth");
            }
        } catch ( IOException | RuntimeException throwables) {
            req.setAttribute("errorMessage",throwables.getMessage());
            resp.sendRedirect("/cv/auth");
        }
    }
}
