package DAO;

import Model.UserTC;
import Service.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class UserTCDAOImpl implements DAO<UserTC> {

    private final Connection connection;

    public UserTCDAOImpl() throws SQLException {
        this.connection = ConnectionHandler.getConnection();
    }

    //language=sql
    private final String findQuery = "SELECT name,surname,email,phonenum,tc,password,birthday usertc FROM usertc WHERE tc = ?";

    @Override
    public Optional<UserTC> find(long tc) {
        try {
            PreparedStatement pS = connection.prepareStatement(findQuery);
            pS.setLong(1,tc);
            ResultSet rS = pS.executeQuery();
            if (rS.next()){
                return Optional.of(new UserTC(rS.getString(1),rS.getString(2),rS.getString(3),rS.getString(4),rS.getLong(5),rS.getString(6),rS.getDate(7).toString()));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }

    //language=sql
    private final String updateQuery = "UPDATE usertc SET name = ?, surname = ?, email = ?,phonenum = ?,password = ?,birthday = ? WHERE tc = ?";


    @Override
    public void update(UserTC user) {
        try {
            PreparedStatement pS = connection.prepareStatement(updateQuery);
            pS.setString(1, user.getName());
            pS.setString(2, user.getSurname());
            pS.setString(3, user.getEmail());
            pS.setString(4, user.getPhoneNum());
            pS.setString(5, user.getPassword());
            pS.setString(6, user.getBirthDay());
            pS.setLong(7,user.getTc());
            int res = pS.executeUpdate();
            if (res == 0){
                throw new SQLException("upd fun didn't work");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    //language=sql
    private final String deleteQuery = "DELETE from usertc where tc = ?";


    @Override
    public void delete(long tc) {
        try {
            PreparedStatement pS = connection.prepareStatement(deleteQuery);
            pS.setLong(1,tc);
            ResultSet rS = pS.executeQuery();
            int res = pS.executeUpdate();
            if (res == 0){
                throw new SQLException("del fun didn't work");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
