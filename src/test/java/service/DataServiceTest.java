package service;

import data.Event;
import exceptions.idException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataServiceTest {

    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = DataService.class.getDeclaredField("ourInstance");
        instance.setAccessible(true);
        instance.set(null, null);
    }



    public void createTestData(DataService dataService)
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
    public void addEvent() {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        assertEquals(4,dataService.size());
//        System.out.println(dataService.toString());

    }

    @Test
    public void editEvent() {
        DataService dataService = DataService.getInstance();
        try {
            dataService.addEvent("1","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(dataService.toString());
    }

    @Test
    @DisplayName("Czy poprawnie usuwa elementy")
    public void removeEvent() throws Exception {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        assertEquals(4,dataService.size(),"Repository size equals 3");
        dataService.removeEvent("2");
//        System.out.println(dataService.toString());
        assertEquals(3,dataService.size());
        dataService.removeEvent(4);
//        System.out.println(dataService.toString());
        assertEquals(2,dataService.size());
    }
    @Test
    @DisplayName("Czy rzuca wyjatkiem o nie istnejacym zdarzeniu")
    public void isThrowingExceptionForNonExistingEvent()
    {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);

        assertThrows(idException.class,()->dataService.removeEvent(5));

    }



    @Test
    @DisplayName("Dodaj 4 eventy i zwroc liste na date 2019-05-15")
    public void getAllEventsForDate() {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        List<Event> today = dataService.GetAllEventsForDate(Timestamp.valueOf("2019-05-15 00:00:00"));
//        System.out.println(today.toString());
        assertEquals(3,today.size());

    }

    @Test
    @DisplayName("Dodaj 4 eventy i usun wszystkie do daty 2019-05-15")
    public void removeEventsToDate() {
        DataService dataService = DataService.getInstance();
        createTestData(dataService);
        try {
            dataService.removeEventsToDate(Timestamp.valueOf("2019-05-15 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(dataService.toString());
        assertEquals(3,dataService.getRepository().size());

    }
}