package Servlets;

import Service.BookingAddingHandler;
import View.Render;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/booking")
public class BusBookingServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Render render =new Render();
        render.renderMap("bus_booking.ftl",new HashMap<>(),resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long tc= 0;
        HttpSession session = req.getSession(false);
        if (session!= null && session.getAttribute("tc")!=null){
            tc = (long)session.getAttribute("tc");
        }
        BookingAddingHandler handler = new BookingAddingHandler();
        handler.addBooking(req,tc);
        Render render =new Render();
        render.renderMap("success.ftl",new HashMap<>(),resp.getWriter());
    }
}
