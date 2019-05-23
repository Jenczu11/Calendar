package service;

import data.Event;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    @Test
    void addEvent() {
        DataService dataService = DataService.getInstance();
        try {
            dataService.addEvent("1","Title1","Title2", Timestamp.valueOf("2019-05-15 18:48:00"),Timestamp.valueOf("2019-05-16 18:48:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataService.toString());

    }
}