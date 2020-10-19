package DAO;

import Model.UserTC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTCDAOMapper implements RowMapper<UserTC> {

    @Override
    public UserTC getInstance(ResultSet resultSet) {
        try {
            return new UserTC(
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getLong(5), resultSet.getString(6),
                    resultSet.getDate(7));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
