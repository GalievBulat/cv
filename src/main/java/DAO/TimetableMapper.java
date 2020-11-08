package DAO;

import Interfaces.RowMapper;
import Model.DayOfTheWeek;
import Model.Station;
import Model.Timetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class TimetableMapper implements RowMapper<Timetable> {
    @Override
    public Timetable getInstance(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("id");
            Station bus_stop = Station.getInstance(resultSet.getString("bus_stop"));
            DayOfTheWeek day_of_the_week =
                    DayOfTheWeek.valueOf(resultSet.getString("day_of_the_week").toUpperCase());
            Time time = resultSet.getTime("time");
            int route_num = resultSet.getInt("route_num");
            return new Timetable.Builder()
                    .id(id)
                    .busStop(bus_stop)
                    .dayOfTheWeek(day_of_the_week)
                    .time(time)
                    .routeNum(route_num)
                    .build();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
