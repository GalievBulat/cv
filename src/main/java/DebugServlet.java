import DAO.CommandHasntWorkedException;
import DAO.TimetableRepositoryImpl;
import DAO.UserTCDAOImpl;
import DAO.UserTCDAOMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            TimetableRepositoryImpl dao = new TimetableRepositoryImpl();

            //language=sql
            /*resp.getWriter().write(dao.query(
                    "SELECT * FROM usertc WHERE phonenum = ?",new UserTCDAOMapper(),"+79518993778").toString());
            */
            resp.getWriter().write(String.valueOf(dao.get(2)));
    }
}
