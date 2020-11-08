package Service;

import DAO.TimetableRepositoryImpl;
import Model.Station;
import Model.Timetable;

import java.sql.Time;
import java.util.List;
public class TimetableSearchingHandler {

    public List<Timetable> findByDWAndTime(Station bS, int dW, Time timeOfStart, Time timeOfFinish){
        TimetableRepositoryImpl repository = new TimetableRepositoryImpl();
        return repository.findByDWAndTime(bS,dW,timeOfStart,timeOfFinish);
    }

    public List<Timetable> findByTime(Station bS,Time timeOfStart,Time timeOfFinish){
        TimetableRepositoryImpl repository = new TimetableRepositoryImpl();
        return repository.findByTime(bS,timeOfStart,timeOfFinish);
    }
}
