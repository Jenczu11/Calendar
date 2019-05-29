package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    @DisplayName("Czy dziala konstruktor i daje dobre dane")
    public void Construct() {
        Event Event1 = new EventBuilder().setId(1).setTitle("Title1").setPlace("Title2").setStartDate(Timestamp.valueOf("2019-05-15 18:48:00")).setEndDate(Timestamp.valueOf("2019-05-16 18:48:00")).createEvent();
//        System.out.println(Event1.toString());
        assertEquals(Event1.getId(),1);

    }
    @ParameterizedTest(name = "For given ID = {0} and Title = {1} it should return description {2}")
    @CsvSource({
            "1,     HUNGARY,    OK, OK",
            "2,    HUNGARY,    INFO, INFO"
    })
    public void checkOtherOptions(int id, String title, String place, String expectedDescription){
        Event event = new EventBuilder().setId(id).setTitle(title).setPlace(place).setStartDate(Timestamp.valueOf("2019-05-15 18:48:00.000")).setEndDate(Timestamp.valueOf("2019-05-16 18:48:00.000")).createEvent();
        assertEquals(event.getPlace(),expectedDescription);

    }



}