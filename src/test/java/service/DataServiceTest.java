package service;

import data.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    @Test
    void addEvent() {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        assertEquals(4,dataService.size());
        System.out.println(dataService.toString());

    }

    @Test
    void editEvent() {
        DataService dataService = DataService.getInstance();
        try {
            dataService.addEvent("1","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataService.toString());
    }

    @Test
    @DisplayName("Czy poprawnie usuwa elementy")
    void removeEvent() throws Exception {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        assertEquals(4,dataService.size(),"Repository size equals 3");
        dataService.removeEvent("2");
        System.out.println(dataService.toString());
        assertEquals(3,dataService.size());
        dataService.removeEvent(4);
        System.out.println(dataService.toString());
        assertEquals(2,dataService.size());
    }
    @Test
    @DisplayName("Czy rzuca wyjatkiem o nie istnejacym zdarzeniu")
    void isThrowingExceptionForNonExistingEvent()
    {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        assertThrows(Exception.class,()->dataService.removeEvent(5));

    }
    void createTestData(DataService dataService)
    {
        try {
            dataService.addEvent("1","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
            dataService.addEvent("2","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
            dataService.addEvent("3","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
            dataService.addEvent("4","Title1","Title2", Timestamp.valueOf("2019-05-13 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    void getAllEventsForDate() {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        List<Event> today = dataService.GetAllEventsForDate(Timestamp.valueOf("2019-05-15 00:00:00"));
        System.out.println(today.toString());
        assertEquals(3,today.size());

    }
}