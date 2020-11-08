package Model;

import Interfaces.Localization;
import Service.RussianLocalization;

import java.sql.Time;

public class Timetable {
    private long id;
    private Station bus_stop;
    private DayOfTheWeek day_of_the_week;
    private Time time;
    private int route_num;


    private Timetable() {
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", bus_stop='" + bus_stop + '\'' +
                ", day_of_the_week=" + day_of_the_week +
                ", time=" + time +
                ", route_num=" + route_num +
                '}';
    }
    public String toJSON(){
        return "{" +
                "\"id\":" + id +
                ", \"bus_stop\":\"" + bus_stop + '\"' +
                ", \"day_of_the_week\":\"" + day_of_the_week + "\""+
                ", \"time\":\"" + time + "\"" +
                ", \"route_num\":" + route_num +
                '}';
    }
    public String toJSONLocalized(Localization localization){
        return "{" +
                "\"id\":" + id +
                ", \"bus_stop\":\"" + bus_stop + '\"' +
                ", \"day_of_the_week\":\"" + localization.localize(day_of_the_week) + "\""+
                ", \"time\":\"" + time + "\"" +
                ", \"route_num\":" + route_num +
                '}';
    }

    public long getId() {
        return id;
    }

    public Station getStation() {
        return bus_stop;
    }
    public String getBus_stopName() {
        return bus_stop.name();
    }


    public Time getTime() {
        return time;
    }

    public int getRoute_num() {
        return route_num;
    }

    public DayOfTheWeek getDay_of_the_week() {
        return day_of_the_week;
    }
    public int getDay_of_the_week_num() {
        return day_of_the_week.ordinal();
    }
    public Boolean[] getDWBooleanArray(){
        Boolean[] b= new Boolean[3];
        int num = getDay_of_the_week_num();
        String bin = Integer.toBinaryString(num);
        for (int i = bin.length()-1; i >=0; i--) {
            b[i] = (bin.charAt(i) == '1');
        }
        return b;
    }
    public static class Builder{
        Timetable timetable;
        public Builder(){
            timetable = new Timetable();
        }
        public Builder id(long id) {
            timetable.id = id;
            return this;
        }
        public Builder busStop(Station bus_stop) {
            timetable.bus_stop = bus_stop;
            return this;
        }
        public Builder dayOfTheWeek(DayOfTheWeek day_of_the_week) {
            timetable.day_of_the_week = day_of_the_week;
            return this;
        }
        public Builder time(Time time) {
            timetable.time = time;
            return this;
        }
        public Builder routeNum(int route_num) {
            timetable.route_num = route_num;
            return this;
        }
        public Timetable build() {
            return timetable;
        }
    }
}
