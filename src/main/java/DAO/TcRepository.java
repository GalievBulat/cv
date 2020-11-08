package DAO;

import Interfaces.JDBCTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TcRepository {
    JDBCTemplate<Long> jdbc= new JDBCTemplateGenericImpl<>();
    //language=sql
    private final static String getBalanceQuery = "Select balance from tc where id = ?";
    public long getBalance(long tc){
        return jdbc.query(getBalanceQuery,(ResultSet rs)-> {
            try {
                return rs.getLong("balance");
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        },tc).get(0);
    }
}
