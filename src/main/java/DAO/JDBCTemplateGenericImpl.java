package DAO;

import Model.Timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplateGenericImpl<T> implements JDBCTemplate<T>{
    @Override
    public List<T> query(String SQLQuery, RowMapper<T> rowMapper, Object... args) {
        List<T> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHandler.getConnection();
            try(connection) {
                PreparedStatement pS = connection.prepareStatement(SQLQuery);
                try (pS) {
                    for (int i = 0; i < args.length; i++)
                        pS.setObject(i+1, args[i]);
                    ResultSet rs = pS.executeQuery();
                    try (rs) {
                        while (rs.next()){
                            list.add(rowMapper.getInstance(rs));
                        }
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
    }


    @Override
    public int update(String SQLQuery, Object... args) {
        try {
            Connection connection = ConnectionHandler.getConnection();
            try(connection) {
                PreparedStatement pS = connection.prepareStatement(SQLQuery);
                try (pS) {
                    for (int i = 0; i < args.length; i++)
                        pS.setObject(i+1, args[i]);
                    return  pS.executeUpdate();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
