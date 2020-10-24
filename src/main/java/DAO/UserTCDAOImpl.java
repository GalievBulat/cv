package DAO;

import Model.UserTC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UserTCDAOImpl implements DAO<UserTC>,Repository<UserTC> {


    private final JDBCTemplate<UserTC> jdbc;

    public UserTCDAOImpl(){
        jdbc = new JDBCTemplateGenericImpl<UserTC>();
    }

    //language=sql
    private final static String findQuery = "SELECT * FROM usertc WHERE tc = ?";

    @Override
    public Optional<UserTC> find(long tc){
        List<UserTC> query =  jdbc.query(findQuery,new UserTCDAOMapper(),tc);
        if (query.isEmpty()){
            return Optional.empty();
        } else
            return Optional.of(query.get(0));
    }

    //language=sql
    private final static String updateQuery = "UPDATE usertc SET name = ?, surname = ?, email = ?,phonenum = ?,password = ?,birthday = ? WHERE tc = ?";


    @Override
    public void update(UserTC user,long tc) {
        jdbc.query(updateQuery,new UserTCDAOMapper(),tc);
    }

    //language=sql
    private final static String deleteQuery = "DELETE from usertc where tc = ?";


    @Override
    public void delete(long tc){
        int res = jdbc.update(deleteQuery,tc);
        if (res == 0){
            throw new RuntimeException();
        }
    }
    @Override
    public void update(UserTC model){
        update(model,model.getTc());
    }

    @Override
    public void delete(UserTC model){
        delete(model.getTc());
    }


    //language=sql
    private final static String addQuery = "INSERT into usertc (name,surname,email,phonenum,tc,password,birthday) VALUES (?,?,?,?,?,?,?)";

    @Override
    public void add(UserTC user){
        int res = jdbc.update(addQuery,
                user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNum(),user.getTc(),user.getPassword(),user.getBirthDay());
        if (res == 0){
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<UserTC> get(int num) {
        return find(num);
    }


    //language=sql
    private final static String checkQuery = "SELECT * FROM usertc WHERE tc = ?" ;

    @Override
    public boolean checkIfPresentedByTC(long tc){
        return !jdbc.query(checkQuery,new UserTCDAOMapper(),tc).isEmpty();
    }


}
