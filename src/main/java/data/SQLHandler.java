package data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za zapis i odczyt do bazy danych
 */
public class SQLHandler implements IOHandler {

    /**
     * Okresla sterownik do polaczenia z baza danych
     */
    private static final String DRIVER = "org.sqlite.JDBC";
    /**
     * Okresla nazwe bazy danych
     */
    private static String DB_URL = "jdbc:sqlite:test.db";
    private Connection conn;

    //TODO: on object creation check if there is a database if not
    // create db and table inside

    /**
     * Konstruktor klasy SQLHandler tworzy baze danych jezeli nie istnieje
     * @throws Exception Wyjatek zwiazany z baza danych
     */
    public SQLHandler() throws Exception
    {
        Class.forName(SQLHandler.DRIVER);
        conn = DriverManager.getConnection(DB_URL);

//        String queryString="create database [IF NOT EXISTS] Eventsdb ";
        // Komenda SQLlite tworzy baze danych jezeli nie istnieje razem z plikiem.
        String queryString="create table IF NOT EXISTS Events " +
                "( id int constraint Events_pk primary key," +
                " title TEXT, " +
                " place TEXT, " +
                "startDate   TEXT, " +
                "endDate     TEXT, " +
                "alarmed     boolean" +
                "); " +
                "create unique index IF NOT EXISTS Events_id_uindex on Events (id);";
        Statement stat = conn.createStatement();
        stat.executeUpdate(queryString);
        conn.close();
    }

    /**
     * Ustawia nazwe bazy danych
     * @param dbUrl nazwa bazy danych
     */
    public static void setDbUrl(String dbUrl) {
        DB_URL = "jdbc:sqlite:"+dbUrl+".db";
    }

    /**
     * Laduje dane z bazy danych
     * @return DataRepository
     * @throws Exception wyjatek zwiazany z baza danych
     */
    @Override
    public DataRepository LoadData() throws Exception {
        EventBuilder builder = new EventBuilder();
        DataRepository dataRepository = new DataRepository();
        Class.forName(SQLHandler.DRIVER);
        conn = DriverManager.getConnection(DB_URL);
        Statement stat = conn.createStatement();
        String queryString = "select id, title, place, startDate, endDate from Events";
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
            builder.setPlace(rs.getString(3));
            builder.setStartDate(Timestamp.valueOf(rs.getString(4)));
            builder.setEndDate(Timestamp.valueOf(rs.getString(5)));
            dataRepository.addEvent(builder.createEvent());
        }
//        System.out.println(dataRepository.toString());
        conn.close();
        return dataRepository;
    }
    /**
     * Zapisuje dane do bazy danych
     * @param data , zawierajace liste wydarzen
     * @throws Exception wyjatek zwiazany z baza danych
     */
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
//            System.out.println(event.getEndDate().toString());
            insert.executeUpdate();
        }
        conn.close();
    }
}
