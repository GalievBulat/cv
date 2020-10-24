package DAO;

import Model.DayOfTheWeek;
import Model.Timetable;
import Model.UserTC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TimetableRepositoryImpl implements Repository<Timetable> {


    private final JDBCTemplate<Timetable> jdbc;
    public TimetableRepositoryImpl() {
        jdbc = new JDBCTemplateGenericImpl<Timetable>();
    }


    //language=sql
    private final static String updateQuery = "UPDATE timetable SET bus_stop = ?,  day_of_the_week= ?, time = ?,route_num = ? WHERE id = ?";

    @Override
    public void update(Timetable model) {
        jdbc.update(updateQuery,model.getBus_stopName(),model.getDWBooleanArray(),model.getTime(),model.getRoute_num(),model.getId());
    }
    //language=sql
    private final static String deleteQuery = "DELETE from timetable where id = ?";

    @Override
    public void delete(Timetable model){
        jdbc.update(updateQuery,model.getId());
    }

    //language=sql
    private final static String addQuery = "INSERT into timetable (id,bus_stop, day_of_the_week, time,route_num) VALUES (?,?,?,?,?)";

    @Override
    public void add(Timetable model)  {
        jdbc.update(addQuery,model.getId(),model.getBus_stopName(),model.getDWBooleanArray(),model.getTime(),model.getRoute_num());
    }


    //language=sql
    private final static String findQuery = "SELECT * FROM timetable WHERE id = ?";

    @Override
    public Optional<Timetable> get(int num) {
        List <Timetable> list = jdbc.query(findQuery,new TimetableMapper(),num);
        if (!list.isEmpty()){
            return Optional.of(list.get(0));
        }else
            return Optional.empty();
    }
}
