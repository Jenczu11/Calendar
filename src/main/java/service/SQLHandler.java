package service;

import data.DataRepository;
import data.Event;
import data.EventBuilder;

import java.sql.*;
import java.util.ArrayList;

public class SQLHandler implements IOHandler{

    private static final String DRIVER = "org.sqlite.JDBC";
    private static String DB_URL = "jdbc:sqlite:test.db";
    private Connection conn;

    public static void setDbUrl(String dbUrl) {
        DB_URL = "jdbc:sqlite:"+dbUrl;
    }

    @Override
    public DataRepository LoadData() throws Exception {
        EventBuilder builder = new EventBuilder();
        DataRepository dataRepository = new DataRepository();
        Class.forName(SQLHandler.DRIVER);
        conn = DriverManager.getConnection(DB_URL);
        Statement stat = conn.createStatement();
        String queryString = "select id, title, description, startDate, endDate from Events";
        ResultSet rs = stat.executeQuery(queryString);
        while (rs.next()) {
//            SimpleDateFormat formatter;
//            formatter = new SimpleDateFormat("MMM dd yyyy hh:mma",Locale.ENGLISH);
//            dataRepository.addEvent(
//                    new Event(
//                    rs.getInt(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    Timestamp.valueOf(rs.getString(4)),
//                   Timestamp.valueOf(rs.getString(5))
//                    )
//            );
            builder.setId(rs.getInt(1));
            builder.setTitle(rs.getString(2));
            builder.setDescription(rs.getString(3));
            builder.setStartDate(Timestamp.valueOf(rs.getString(4)));
            builder.setEndDate(Timestamp.valueOf(rs.getString(5)));
            dataRepository.addEvent(builder.createEvent());
        }
        System.out.println(dataRepository.toString());
        conn.close();
        return dataRepository;
    }

    @Override
    public void SaveData(DataRepository data) throws Exception {
        ArrayList<Event> events = data.getAllEvents();
        Class.forName(SQLHandler.DRIVER);
        conn = DriverManager.getConnection(DB_URL);
        PreparedStatement delStat = conn.prepareStatement("DELETE FROM Events");
        delStat.executeUpdate();
        for (Event event : events)
        {
            PreparedStatement insert = conn.prepareStatement("Insert INTO Events VALUES "+
                    "(?,?,?,?,?,?)");
            insert.setInt(1,event.getId());
            insert.setString(2,event.getTitle());
            insert.setString(3,event.getPlace());
            insert.setString(4,event.getStartDate().toString());
            insert.setString(5,event.getEndDate().toString());
            insert.setBoolean(6,event.isAlarm());
            System.out.println(event.getEndDate().toString());
            insert.executeUpdate();
        }
        conn.close();
    }
}
