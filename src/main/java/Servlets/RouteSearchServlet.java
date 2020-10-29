package Servlets;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

//@WebServlet("/timetable")
public class RouteSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Render render = new Render();
        try {
            render.renderMap("routes.ftl", Collections.emptyMap(),resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    //day_of_the_week,*,station,PSH,time1,00:00,time2,22:00
    //day_of_the_week:*,station:PSH,time1:00:00}
    //[}"{]+[":"]+

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        BufferedReader iS = req.getReader();
        String json = iS.readLine().replaceAll("(\":\")+",",").replaceAll("[}\"{]+","");
        String[] strings = json.split(",");
        String station = strings[0].equals("station")? strings[1] : "";
        String time1 =  strings[2].equals("time1")? strings[3] : "00:00";
        String time2 =  strings[4].equals("time2")? strings[5] : "24:00";
        String dW = strings[6].equals("day_of_the_week")? strings[7] : "*";
        RouteConvertingHandler rh = new RouteConvertingHandler();
        Time timeOfStart = rh.convert(time1,true);
        Time timeOfFinish = rh.convert(time2,false);
        TimetableSearchingHandler sH = new TimetableSearchingHandler();
        List<Timetable> tList;
        if (dW.equals("*")){
            tList = sH.findByTime(Station.valueOf(station),timeOfStart,timeOfFinish);
        } else
            tList = sH.findByDWAndTime(Station.valueOf(station),Integer.parseInt(dW),timeOfStart,timeOfFinish);
        List<String> result= new ArrayList<>();
        for(Timetable u: tList){
            result.add(u.toJSON());
        }
        resp.getWriter().write(result.toString());
        resp.setStatus(200);
    }
}
