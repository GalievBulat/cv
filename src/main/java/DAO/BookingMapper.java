package DAO;

import Interfaces.RowMapper;
import Model.Booking;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class BookingMapper implements RowMapper<Booking> {
    public Booking getInstance(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("id");
            String destination = resultSet.getString("destination");
            String busModel =  resultSet.getString("busModel");
            Time time = resultSet.getTime("time");
            Date date = resultSet.getDate("date");
            long tc =  resultSet.getLong("tc");
            String ph_num =  resultSet.getString("ph_num");
            return new Booking.Builder()
                    .id(id)
                    .destination(destination)
                    .busModel(busModel)
                    .time(time)
                    .date(date)
                    .tc(tc)
                    .ph_num(ph_num)
                    .build();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
