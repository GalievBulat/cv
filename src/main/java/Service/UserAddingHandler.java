package Service;

import DAO.CommandHasntWorkedException;

import javax.naming.InvalidNameException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.List;

public interface UserAddingHandler {
    public void addUser(List<String> userData) throws InvalidNameException;
}
