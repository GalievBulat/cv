package Model;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    private long id;
    private String destination;
    private String busModel;
    private Time time;
    private Date date;
    private long tc;
    private String ph_num;
    private Booking(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTc() {
        return tc;
    }

    public void setTc(long tc) {
        this.tc = tc;
    }

    public String getPh_num() {
        return ph_num;
    }

    public void setPh_num(String ph_num) {
        this.ph_num = ph_num;
    }
    public static class Builder{
        private Booking booking;
        public Builder(){
             booking= new Booking();
        }
        public Builder id(long id) {
            booking.id = id;
            return this;
        }

        public Builder destination(String destination) {
            booking.destination = destination;
            return this;
        }

        public Builder busModel(String busModel) {
            booking.busModel = busModel;
            return this;
        }

        public Builder time(Time time) {
            booking.time = time;
            return this;
        }

        public Builder date(Date date) {
            booking.date = date;
            return this;
        }

        public Builder tc(long tc) {
            booking.tc = tc;
            return this;
        }

        public Builder ph_num(String ph_num) {
            booking.ph_num = ph_num;
            return this;
        }

        public Booking build() {
            return booking;
        }

    }
}
