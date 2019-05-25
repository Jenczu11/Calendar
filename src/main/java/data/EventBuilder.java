package data;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventBuilder {
    private int id;
    private String title;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;


    public EventBuilder setId(int id) {

        this.id = id;
        return this;
    }
    public EventBuilder setId(String idValue) {
        if(idValue.isBlank())
            idValue=Integer.toString((int)Math.random()*100);
        this.id = Integer.parseInt(idValue);
        return this;
    }

    public EventBuilder setTitle(String title) {
        if(title.isBlank())
            title="Default Title";
        this.title = title;
        return this;
    }

    public EventBuilder setDescription(String description) {
        if(description.isBlank())
            description="Default Description";
        this.description = description;
        return this;
    }

    public EventBuilder setStartDate(Timestamp startDate) {

        this.startDate = startDate;
        return this;
    }

    public EventBuilder setEndDate(Timestamp endDate) {

        this.endDate = endDate;
        return this;
    }

    public Event createEvent() {
        return new Event(id, title, description, startDate, endDate);
    }
}