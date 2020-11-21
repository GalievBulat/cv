package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.Repository;
import Model.Booking;

import java.util.List;
import java.util.Optional;

public class BookingRepository implements Repository<Booking> {

    //language=sql
    private final static String  addingQuery = "insert into booking (bus_model,tc,tel_num,destination,date,time) values (?::BusModel,?,?,?,?,?);";

    //language=sql
    private final static String  updateQuery = "UPDATE booking SET bus_model = ?::BusModel,tc = ?,tel_num = ?,destination = ?,date = ?,time = ? where id=?;";
    //language=sql
    private final static String  deleteQuery = "DELETE from booking where id=?;";
    //language=sql
    private final static String  getQuery = "SELECT * FROM booking WHERE id = ?";
    private final JDBCTemplate<Booking> jdbc = new JDBCTemplateGenericImpl<>();


    @Override
    public void update(Booking model) {
        if (jdbc.update(updateQuery,model.getBusModel(),model.getTc(),model.getPh_num(),model.getDestination(),model.getDate(),model.getTime(),model.getId())==0){
            throw new RuntimeException("hasnt worked");
        }
    }

    @Override
    public void delete(Booking model) {
        if (jdbc.update(deleteQuery,model.getId()) == 0){
            throw new RuntimeException("hasnt worked");
        }

    }

    public void add(Booking model) {
        if (jdbc.update(addingQuery,model.getBusModel(),model.getTc(),model.getPh_num(),model.getDestination(),model.getDate(),model.getTime())==0){
            throw new RuntimeException("hasnt worked");
        }
    }

    @Override
    public Optional<Booking> get(long num) {
        List<Booking> list = jdbc.query(getQuery,new BookingMapper(),num);
        if (list.size()>0)
            return Optional.of(jdbc.query(getQuery,new BookingMapper(),num).get(0));
        else return Optional.empty();
    }

}
