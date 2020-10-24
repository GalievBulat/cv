package Model;

import java.sql.Time;

public class Timetable {
    private final long id;
    private final Station bus_stop;
    private final DayOfTheWeek day_of_the_week;
    private final Time time;
    private final int route_num;
    public Timetable(long id, String bus_stop, DayOfTheWeek day_of_the_week, Time time, int route_num) {
        this.id = id;
        this.bus_stop = Station.getInstance(bus_stop);
        this.day_of_the_week = day_of_the_week;
        this.time = time;
        this.route_num = route_num;
    }
    public Timetable(long id, String bus_stop, String day_of_the_week, Time time, int route_num) {
        this.id = id;
        this.bus_stop = Station.getInstance(bus_stop);
        this.day_of_the_week = DayOfTheWeek.valueOf(day_of_the_week.toUpperCase());
        this.time = time;
        this.route_num = route_num;
    }
    private int bitArrayToInt(Boolean[] day_of_the_week){
        int res = 0;
        for (int i = 0; i < day_of_the_week.length; i++) {
            if (day_of_the_week[i]){
                res = res<<1;
                res++;
            }
        }
        return res;
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
                "id=" + id +
                ", bus_stop='" + bus_stop + '\'' +
                ", day_of_the_week=" + day_of_the_week +
                ", time=" + time +
                ", route_num=" + route_num +
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
}
