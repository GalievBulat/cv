package DAO;

import Interfaces.RowMapper;
import Model.UserTC;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTCMapper implements RowMapper<UserTC> {

    @Override
    public UserTC getInstance(ResultSet resultSet) {
        try {
            String name=resultSet.getString("name");
            String surname=resultSet.getString("surname");
            String email=resultSet.getString("email");
            String phoneNum=resultSet.getString("phonenum");
            long tc=resultSet.getLong("tc");
            String password=resultSet.getString("password");
            Date birthDay=resultSet.getDate("birthday");
            String avatarPath =resultSet.getString("avatar_path");
            return new UserTC.Builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .phoneNum(phoneNum)
                    .tc(tc)
                    .password(password)
                    .birthDay(birthDay)
                    .avatarPath(avatarPath)
                    .build();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
