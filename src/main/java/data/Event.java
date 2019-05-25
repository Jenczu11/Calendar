package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private boolean alarmed;

    public Event() {
    }

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
    @XmlAttribute(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @XmlAttribute(name="alarmed")
    public boolean isAlarmed() {
        return alarmed;
    }

    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy na Timestamp
     * @param ddMMyyyy String do parsownia
     * @return Zparsowany string na timestamp
     */
    public static Timestamp StringToTimestamp(String ddMMyyyy) {
        if (ddMMyyyy.isBlank())
            ddMMyyyy="24/05/2019";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(ddMMyyyy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(parsedDate.getTime());
    }
    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy HH:mm na Timestamp
     * @param time String do parsownia
     * @return Zparsowany string na timestamp
     */

    public static Timestamp StringToTimestampWithTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(parsedDate.getTime());
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
