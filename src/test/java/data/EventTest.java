package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    @DisplayName("Czy dziala konstruktor i daje dobre dane")
    void Construct() {
        Event Event1 = new Event(1,"Title1","Title2",Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
        System.out.println(Event1.toString());
        assertEquals(Event1.getId(),1);

    }
    @ParameterizedTest(name = "For given ID = {0} and Title = {1} it should return description {2}")
    @CsvSource({
            "1,     HUNGARY,    OK, OK",
            "2,    HUNGARY,    INFO, INFO"
    })
    void checkOtherOptions(int id, String title, String description, String expectedDescription){
        Event event = new Event(id,title,description,Timestamp.valueOf("2019-05-15 18:48:00.000"),Timestamp.valueOf("2019-05-16 18:48:00.000"));
        assertEquals(event.getDescription(),expectedDescription);

    }



}