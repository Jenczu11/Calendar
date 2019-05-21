package Data;

import java.util.Date;
import java.util.GregorianCalendar;

public class Event {
    /**
     * Id wydarzenia
     */
    private int id;
    /**
     * Tytul wydarzenia
     */
    private String title;
    /**
     * Opis wydarzenia
     */
    private String description;
    /**
     * Data rozpoczecia wydarzenia
     */
    private GregorianCalendar startDate;
    /**
     * Data zakonczenia wydarzenia
     */
    private GregorianCalendar endDate;
    /**
     * Zmienna informujaca, czy wystapi� ju� alarm przypisany do danego wydarzenia
     */
    public boolean alarmed;

    public Event(int id, String title, String description, GregorianCalendar startDate, GregorianCalendar endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        alarmed=false;
    }

    /**
    Gettery i settery
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }
}
