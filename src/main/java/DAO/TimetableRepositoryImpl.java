package DAO;

import Interfaces.JDBCTemplate;
import Interfaces.Repository;
import Model.DayOfTheWeek;
import Model.Station;
import Model.Timetable;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public class TimetableRepositoryImpl implements Repository<Timetable> {


    private final JDBCTemplate<Timetable> jdbc;
    public TimetableRepositoryImpl() {
        jdbc = new JDBCTemplateGenericImpl<>();
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
    public Optional<Timetable> get(long num) {
        List <Timetable> list = jdbc.query(findQuery,new TimetableMapper(),num);
        if (!list.isEmpty()){
            return Optional.of(list.get(0));
        }else
            return Optional.empty();
    }
    //language=sql
    private final static String findByDWAndTimeQuery ="SELECT * FROM timetable  WHERE bus_stop = ? AND day_of_the_week = ?::dayoftheweek AND time>=? AND time<=?";

    public List<Timetable> findByDWAndTime(Station bS, int dW, Time timeOfStart, Time timeOfFinish){
        return jdbc.query(findByDWAndTimeQuery, new TimetableMapper(),
                bS.getName(), DayOfTheWeek.values()[dW-1].name().toLowerCase(), timeOfStart, timeOfFinish);
    }

    //language=sql
    private final static String findByTimeQuery = "SELECT * FROM timetable WHERE bus_stop = ? AND time>=? AND time<=?";

    public List<Timetable> findByTime(Station bS,Time timeOfStart,Time timeOfFinish){
        return jdbc.query(findByTimeQuery,new TimetableMapper(),bS.getName(), timeOfStart,timeOfFinish);
    }
}
