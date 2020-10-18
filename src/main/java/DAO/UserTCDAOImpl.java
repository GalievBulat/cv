package DAO;

import Model.UserTC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class UserTCDAOImpl implements DAO<UserTC>,Repository<UserTC>,AutoCloseable {

    private final Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    public UserTCDAOImpl() throws SQLException {
        this.connection = ConnectionHandler.getConnection();
    }

    //language=sql
    private final static String findQuery = "SELECT name,surname,email,phonenum,tc,password,birthday usertc FROM usertc WHERE tc = ?";

    @Override
    public Optional<UserTC> find(long tc) throws CommandHasntWorkedException {
        try {
            PreparedStatement pS = connection.prepareStatement(findQuery);
            try(pS) {
                pS.setLong(1, tc);
                ResultSet rS = pS.executeQuery();
                try(rS) {
                    if (rS.next()) {
                        return Optional.of(new UserTC(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getLong(5), rS.getString(6), rS.getDate(7)));
                    }
                    return Optional.empty();
                }
            }
        }catch (SQLException e){
            throw new CommandHasntWorkedException(e.getMessage());
        }
    }

    //language=sql
    private final static String updateQuery = "UPDATE usertc SET name = ?, surname = ?, email = ?,phonenum = ?,password = ?,birthday = ? WHERE tc = ?";


    @Override
    public void update(UserTC user,long tc)throws CommandHasntWorkedException {
        try {
            PreparedStatement pS = connection.prepareStatement(updateQuery);
            try(pS) {
                pS.setString(1, user.getName());
                pS.setString(2, user.getSurname());
                pS.setString(3, user.getEmail());
                pS.setString(4, user.getPhoneNum());
                pS.setString(5, user.getPassword());
                pS.setDate(6, user.getBirthDay());
                pS.setLong(7, tc);
                int res = pS.executeUpdate();
                if (res == 0) {
                    throw new CommandHasntWorkedException("upd fun didn't work");
                }
            }
        }catch (SQLException e){
            throw new CommandHasntWorkedException(e.getMessage());
        }
    }

    //language=sql
    private final static String deleteQuery = "DELETE from usertc where tc = ?";


    @Override
    public void delete(long tc) throws CommandHasntWorkedException {
        try {
            PreparedStatement pS = connection.prepareStatement(deleteQuery);
            try(pS) {
                pS.setLong(1, tc);
                int res = pS.executeUpdate();
                if (res == 0) {
                    throw new CommandHasntWorkedException("del fun didn't work");
                }
            }
        } catch (SQLException e){
            throw new CommandHasntWorkedException(e.getMessage());
        }
    }
    //language=sql
    private final static String addQuery = "INSERT into usertc (name,surname,email,phonenum,tc,password,birthday) VALUES (?,?,?,?,?,?,?)";

    @Override
    public void update(UserTC model) throws CommandHasntWorkedException {
        update(model,model.getTc());
    }

    @Override
    public void delete(UserTC model) throws CommandHasntWorkedException {
        delete(model.getTc());
    }

    @Override
    public void add(UserTC user) throws CommandHasntWorkedException {
        try {
            PreparedStatement pS = connection.prepareStatement(addQuery);
            try(pS) {
                pS.setString(1, user.getName());
                pS.setString(2, user.getSurname());
                pS.setString(3, user.getEmail());
                pS.setString(4, user.getPhoneNum());
                pS.setLong(5, user.getTc());
                pS.setString(6, user.getPassword());
                pS.setDate(7, user.getBirthDay());
                int res = pS.executeUpdate();
                if (res == 0) {
                    throw new CommandHasntWorkedException("upd fun didn't work");
                }
            }
        } catch (SQLException e){
            throw new CommandHasntWorkedException(e.getMessage());
        }
    }

    @Override
    public List<UserTC> query(Specification<UserTC> specification) {
        return null;
    }

    //language=sql
    private final static String checkQuery = "SELECT tc FROM usertc WHERE tc = ?" ;

    @Override
    public boolean checkIfPresentedByTC(long tc) throws CommandHasntWorkedException {
        try {
            PreparedStatement pS = connection.prepareStatement(checkQuery);
            try(pS) {
                pS.setLong(1, tc);
                ResultSet rs = pS.executeQuery();
                try(rs) {
                    return rs.next();
                }
            }
        }catch (SQLException e){
            throw new CommandHasntWorkedException(e.getMessage());
        }
    }
    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) { throw new RuntimeException(e.getMessage());}

    }
}
