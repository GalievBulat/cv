package Service;

import java.sql.Time;

public class RouteConvertingHandler {
    public Time convert(String time,boolean last){
        if (time.equals("")){
            if (last)
                return Time.valueOf("23:59:59");
            else return Time.valueOf("00:00:00");
        }else
            return Time.valueOf(java.net.URLDecoder.decode(time) + ":00");
    }
}
