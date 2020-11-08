package Interfaces;

import java.util.List;

public interface JDBCTemplate<T> {
    List<T> query(String SQLQuery,RowMapper<T> rowMapper, Object... args);
    int update(String SQLQuery, Object... args);
}
