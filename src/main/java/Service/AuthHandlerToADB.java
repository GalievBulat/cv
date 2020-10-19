package Service;

import DAO.CommandHasntWorkedException;
import DAO.DAO;
import DAO.UserTCDAOImpl;
import Model.UserTC;

import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthHandlerToADB implements AuthHandler {
    @Override
    public Optional<UserTC> authoriseByTC(long tc, String password) throws InvalidKeyException {
        DAO<UserTC> dao = new UserTCDAOImpl();
        Optional<UserTC> oUser = dao.find(tc);
        if (oUser.isPresent()){
            UserTC user = oUser.get();
            if (user.getPassword().equals(password))
                return oUser;
        }else {
            throw new InvalidKeyException("no user found for this tc");
        }
        return Optional.empty();
    }
}
