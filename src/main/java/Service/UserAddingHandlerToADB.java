package Service;

import DAO.CommandHasntWorkedException;
import DAO.DAO;
import DAO.UserTCDAOImpl;
import Model.UserTC;

import javax.naming.InvalidNameException;
import java.sql.SQLException;
import java.util.List;

public class UserAddingHandlerToADB implements UserAddingHandler {

    @Override
    public void addUser(List<String> userData) throws InvalidNameException,SQLException, CommandHasntWorkedException {
        DAO<UserTC> dao = new UserTCDAOImpl();
        UserTC user = new UserTC(userData);
        synchronized (dao.getConnection()) {
            if (user.getPhoneNum().matches("\\+\\d{11}")) {
                dao.add(user);
            }else {
                throw new InvalidNameException("not a number");
            }
        }
    }
}
