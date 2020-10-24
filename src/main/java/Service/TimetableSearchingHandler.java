package Service;

import DAO.JDBCTemplate;
import DAO.JDBCTemplateGenericImpl;
import DAO.TimetableMapper;
import Model.DayOfTheWeek;
import Model.Station;
import Model.Timetable;

import java.sql.Time;
import java.util.List;
public class TimetableSearchingHandler {
    private final JDBCTemplate<Timetable> jdbc = new JDBCTemplateGenericImpl<Timetable>();

    //language=sql
    private final static String findByDWAndTimeQuery ="SELECT * FROM timetable  WHERE bus_stop LIKE ? AND day_of_the_week = ?::dayoftheweek AND time>=? AND time<=?";

    public List<Timetable> findByDWAndTime(Station bS, int dW, Time timeOfStart, Time timeOfFinish){
            return jdbc.query(findByDWAndTimeQuery, new TimetableMapper(),
                    bS.getName(), DayOfTheWeek.values()[dW].name().toLowerCase(), timeOfStart, timeOfFinish);
    }

    //language=sql
    private final static String findByTimeQuery = "SELECT * FROM timetable WHERE bus_stop LIKE ? AND time>=? AND time<=?";

    public List<Timetable> findByTime(Station bS,Time timeOfStart,Time timeOfFinish){
        return jdbc.query(findByTimeQuery,new TimetableMapper(),bS.getName(), timeOfStart,timeOfFinish);
    }
}
