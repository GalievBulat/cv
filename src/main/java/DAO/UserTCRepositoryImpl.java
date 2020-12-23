package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.Repository;
import Model.UserTC;

import java.util.List;
import java.util.Optional;

public final class UserTCRepositoryImpl implements Repository<UserTC> {


    private final JDBCTemplate<UserTC> jdbc;

    public UserTCRepositoryImpl(){
        jdbc = new JDBCTemplateGenericImpl<UserTC>();
    }

    //language=sql
    private final static String updateQuery = "UPDATE usertc SET name = ?, surname = ?, email = ?,phonenum = ?,birthday = ? WHERE tc = ?";

    @Override
    public void update(UserTC model){
        jdbc.update(updateQuery,model.getName(),model.getSurname(),model.getEmail(),model.getPhoneNum(),model.getBirthDay(),model.getTc());
    }

    //language=sql
    private final static String deleteQuery = "DELETE from usertc where tc = ?";


    @Override
    public void delete(UserTC model){
        int res = jdbc.update(deleteQuery,model.getTc());
        if (res == 0){
            throw new RuntimeException();
        }
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

    //language=sql
    private final static String findQuery = "SELECT * FROM usertc WHERE tc = ?";


    @Override
    public Optional<UserTC> get(long num) {
        List<UserTC> query =  jdbc.query(findQuery,new UserTCMapper(),num);
        if (query.isEmpty()){
            return Optional.empty();
        } else
            return Optional.of(query.get(0));
    }


}
