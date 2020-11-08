package Service;

import DAO.BookingRepository;
import Model.Booking;
import Model.UserTC;

import javax.servlet.ServletRequest;
import java.sql.Date;

public class BookingAddingHandler {
    BookingRepository repository = new BookingRepository();
    public void addBooking(ServletRequest req,long tc){
        final String time = req.getParameter("time");
        final String phoneNum = req.getParameter("phone_num");
        final String spot = req.getParameter("spot");
        final String date = req.getParameter("date");
        final String bus = req.getParameter("bus");
        if (spot== null || spot.equals(""))
            throw new RuntimeException("not a name");
        if (bus== null || !bus.matches("\\d{1}"))
            throw new RuntimeException("not a bus num");
        if (phoneNum== null ||!phoneNum.matches("\\+\\d{11}"))
            throw new RuntimeException("not a number");
        if (time== null || !time.matches("\\d{2}\\:\\d{2}"))
            throw new RuntimeException("wrong time format");
        if (date== null || !date.matches("\\d{4}\\-\\d{2}\\-\\d{2}"))
            throw new RuntimeException("wrong date format");
        RouteConvertingHandler handler = new RouteConvertingHandler();
        Booking booking = new Booking.Builder()
                .time(handler.convert(time,false))
                .destination(spot)
                .busModel(bus)
                .ph_num(phoneNum)
                .tc(tc)
                .date(Date.valueOf(date)).build();
        repository.add(booking);
    }
}
