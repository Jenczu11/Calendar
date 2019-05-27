package service;

import data.DataRepository;
import data.Event;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SQLHandler implements IOHandler{

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:test.db";
    private Connection conn;
    private Statement stat;

    @Override
    public DataRepository LoadData() throws Exception {
        DataRepository dataRepository = new DataRepository();
        Class.forName(SQLHandler.DRIVER);
        conn = DriverManager.getConnection(DB_URL);
        stat = conn.createStatement();
        String queryString = "select id, title, description, startDate, endDate from Events";
        ResultSet rs = stat.executeQuery(queryString);
        while (rs.next()) {
//            SimpleDateFormat formatter;
//            formatter = new SimpleDateFormat("MMM dd yyyy hh:mma",Locale.ENGLISH);
            dataRepository.addEvent(
                    new Event(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    Timestamp.valueOf(rs.getString(4)),
                   Timestamp.valueOf(rs.getString(5))
                    )
            );
        }
        System.out.println(dataRepository.toString());
        conn.close();
        return dataRepository;
    }

    @Override
    public void SaveData(DataRepository data) throws Exception {

    }
}
