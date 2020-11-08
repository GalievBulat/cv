package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.Repository;
import Model.Booking;
import Model.Timetable;

import java.util.Optional;

public class BookingRepository  {

    //language=sql
    private final static String  addingQuery = "insert into booking (bus_model,tc,tel_num,destination,date,time) values (?::BusModel,?,?,?,?,?);";

    private final JDBCTemplate<Booking> jdbc = new JDBCTemplateGenericImpl<>();
    public void add(Booking model) {
        if (jdbc.update(addingQuery,model.getBusModel(),model.getTc(),model.getPh_num(),model.getDestination(),model.getDate(),model.getTime())==0){
            throw new RuntimeException("hasnt worked");
        }
    }

}
