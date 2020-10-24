import Model.Station;
import Model.Timetable;
import Service.RouteConvertingHandler;
import Service.TimetableSearchingHandler;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/timetable")
public class RouteSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Render render = new Render();
        try {
            render.renderMap("routes.ftl", Collections.emptyMap(),resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String inputString= req.getReader().lines().collect(Collectors.joining());
        String[] strings = inputString.split("&");
        String dW = strings[0].replace("day_of_the_week=","");
        String station = strings[1].replace("station=","");
        String time1 =  strings[2].replace("time1=","");
        String time2 =  strings[3].replace("time2=","");
        /*String dW = req.getParameter("day_of_the_week");
        String station = req.getParameter("station");
        String time1 = req.getParameter("time1");
        String time2 = req.getParameter("time2");
        */
        RouteConvertingHandler rh = new RouteConvertingHandler();
        Time timeOfStart = rh.convert(time1,true);
        Time timeOfFinish = rh.convert(time2,false);
        TimetableSearchingHandler sH = new TimetableSearchingHandler();
        List<Timetable> tList;
        if (dW.equals("*")){
            tList = sH.findByTime(Station.valueOf(station),timeOfStart,timeOfFinish);
        } else
            tList = sH.findByDWAndTime(Station.valueOf(station),Integer.parseInt(dW),timeOfStart,timeOfFinish);
        /*Render render = new Render();
        Map<String,Object> root = new HashMap<>();
        root.put("tList",tList);
        try {
            render.renderMap("routes.ftl",root,resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }*/
        List<String> result= new ArrayList<>();
        for(Timetable u: tList){
            result.add(u.toJSON());
        }
        resp.getWriter().write(result.toString());
        resp.setStatus(200);
    }
}
