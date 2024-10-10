package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private String time;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezoneParam = req.getParameter("timezone");
        ZoneId zoneId;
        if(timezoneParam != null &&!timezoneParam.isEmpty())
        {
            if (timezoneParam.contains(" ")) {
                timezoneParam = timezoneParam.replace(" ", "+");
            }
            zoneId = ZoneId.of(timezoneParam);
        }
        else if(timezoneParam.equals("UTC")) {
            zoneId = ZoneId.of("UTC");
        }
        else  {
            zoneId = ZoneId.of("UTC");
        }
        ZonedDateTime dateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = dateTime.format(formatter)+" "+ zoneId;
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(time);
        resp.getWriter().close();
    }
}
