package Interfaces;


import javax.naming.InvalidNameException;
import javax.servlet.ServletRequest;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.List;

public interface UserAddingHandler {
    public void addUser(ServletRequest req);
}
