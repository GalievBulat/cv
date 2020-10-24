package DAO;

import Model.Timetable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimetableMapper implements RowMapper<Timetable> {
    @Override
    public Timetable getInstance(ResultSet resultSet) {
        try {
            return new Timetable(resultSet.getLong("id"),
                    resultSet.getString("bus_stop"),
                    resultSet.getString("day_of_the_week"),
                    resultSet.getTime("time"),
                    resultSet.getInt("route_num"));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
