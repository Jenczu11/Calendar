package data;

import java.sql.Timestamp;

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
     * data rozpoczecia wydarzenia
     */
    private Timestamp startDate;
    /**
     * data zakonczenia wydarzenia
     */
    private Timestamp endDate;
    /**
     * Zmienna informujaca, czy wystapi� ju� alarm przypisany do danego wydarzenia
     */
    public boolean alarmed;

    public Event(int id, String title, String description, Timestamp startDate, Timestamp endDate) {
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", alarmed=" + alarmed +
                '}';
    }
}
