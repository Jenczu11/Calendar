package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;

@XmlRootElement(name="event")
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
     * Miejsce wydarzenia
     */
    private String place;
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
    private boolean alarm;

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public Event() {
    }

    public Event(int id, String title, String place, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        alarm = false;
    }

    /**
    Gettery i settery
     */
    @XmlAttribute(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlAttribute(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlAttribute(name="place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    @XmlTransient
    public Timestamp getStartDate() {
        return startDate;
    }

    @XmlAttribute(name="startDate")
    public String getStartDateToXml() {return startDate.toString();}
    public void setStartDateToXml(String startDate) {this.startDate=Timestamp.valueOf(startDate);}

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    @XmlTransient
    public Timestamp getEndDate() {
        return endDate;
    }

    @XmlAttribute(name="endDate")
    public String getEndDateToXml() {return endDate.toString();}
    public void setEndDateToXml(String endDate) {this.endDate=Timestamp.valueOf(endDate);}


    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @XmlAttribute(name="alarm")
    public boolean isAlarm() {
        return alarm;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", alarm=" + alarm +
                '}';
    }

}
