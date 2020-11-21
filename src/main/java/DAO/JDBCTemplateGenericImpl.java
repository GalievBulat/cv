package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplateGenericImpl<T> implements JDBCTemplate<T>{
    Connection connection = ConnectionHandler.getConnection();

    @Override
    public List<T> query(String SQLQuery, RowMapper<T> rowMapper, Object... args) {
        List<T> list = new ArrayList<>();
        try {
            synchronized (connection) {
                if (connection.isClosed())
                    connection = ConnectionHandler.getConnection();
                PreparedStatement pS = connection.prepareStatement(SQLQuery);
                try (pS) {
                    for (int i = 0; i < args.length; i++)
                        pS.setObject(i + 1, args[i]);
                    ResultSet rs = pS.executeQuery();
                    try (rs) {
                        while (rs.next()) {
                            list.add(rowMapper.getInstance(rs));
                        }
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ignore) { }
        }
        return list;
    }


    @Override
    public int update(String SQLQuery, Object... args) {
        Connection connection = ConnectionHandler.getConnection();
        try {
            synchronized (connection) {
                if (connection.isClosed())
                    connection = ConnectionHandler.getConnection();
                PreparedStatement pS = connection.prepareStatement(SQLQuery);
                try (pS) {
                    for (int i = 0; i < args.length; i++)
                        pS.setObject(i + 1, args[i]);
                    return pS.executeUpdate();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ignore) { }
        }
    }
}
