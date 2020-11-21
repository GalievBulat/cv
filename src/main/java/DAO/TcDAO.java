package DAO;

import Interfaces.DAO;
import Interfaces.JDBCTemplate;
import Model.Tc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TcDAO implements DAO<Tc> {
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

    @Override
    public Optional<Tc> find(long id) {
        throw new RuntimeException("no right to do so");
    }

    @Override
    public void update(Tc model, long tc) {
        throw new RuntimeException("no right to do so");
    }

    @Override
    public void delete(long id) {
        throw new RuntimeException("no right to do so");
    }

    @Override
    public void add(Tc model) {
        throw new RuntimeException("no right to do so");
    }
}
