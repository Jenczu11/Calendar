package service;

import data.EventBuilder;
import data.GoogleCalendarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleCalendarHandlerTest {
    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = DataService.class.getDeclaredField("ourInstance");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @ParameterizedTest(name = "For all empty strings it should dataRepo must have 10 events, and must save to XML")
    @CsvSource({
            "'', '', '','','','',''",
    })
    public void saveData(String id, String title, String description, String startDate, String endDate) {
        DataService dataService = DataService.getInstance();
        int expected;
        for (expected = 0; expected < 10; expected++) {
            EventBuilder builder = new EventBuilder();
            try {
                dataService.refreshLastEventID();
                builder.setId(id);
                builder.setTitle(title);
                builder.setPlace(description);
                builder.setStartDate(dataService.StringToTimestamp(startDate));
                builder.setEndDate(dataService.StringToTimestamp(endDate));
                dataService.addEvent(builder.createEvent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assertEquals(expected, dataService.size());
        GoogleCalendarHandler gcalh = new GoogleCalendarHandler("GcalTest");
        assertDoesNotThrow(() -> dataService.saveRepository(gcalh));

    }
}