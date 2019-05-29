package service;

import data.EventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLHandlerTest {
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
        for(expected=0; expected<10;expected++) {
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
        assertEquals(expected,dataService.getRepositoryEvents().size());
        XMLHandler xmlHandler = new XMLHandler("XMLHandlerTest.xml");
        assertDoesNotThrow(() -> dataService.saveRepository(xmlHandler));
//



    }

    @Test
    @DisplayName("Should load new instance of DataService and load database for XML file, expected 10 events if run with previous Test")
    public void loadData() {
        DataService dataService = DataService.getInstance();
        int expected=10;
        XMLHandler xmlHandler = new XMLHandler("XMLHandlerTest.xml");
        assertDoesNotThrow(()->dataService.loadRepository(xmlHandler));
        assertEquals(expected,dataService.getRepositoryEvents().size());
//        System.out.println(dataService.toString());

    }
}