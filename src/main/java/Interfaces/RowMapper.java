package Interfaces;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T getInstance(ResultSet resultSet);
}
