package DAO;

import Model.UserTC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTCDAOMapper implements RowMapper<UserTC> {

    @Override
    public UserTC getInstance(ResultSet resultSet) {
        try {
            return new UserTC(
                    resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("email"),
                    resultSet.getString("phonenum"), resultSet.getLong("tc"), resultSet.getString("password"),
                    resultSet.getDate("birthday"),resultSet.getString("avatar_path"));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
