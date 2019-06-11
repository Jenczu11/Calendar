package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;

/**
 * Klasa reprezentujaca pojedyncze wydarzenie
 */
@XmlRootElement(name = "event")
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
     * Data rozpoczecia wydarzenia
     */
    private Timestamp startDate;
    /**
     * Data zakonczenia wydarzenia
     */
    private Timestamp endDate;
    /**
     * Zmienna informujaca, czy wystapil juz alarm przypisany do danego wydarzenia
     */
    private boolean alarm;

    /**
     * Ustawia czy event byl juz przypominany
     * @param alarm czy event byl juz przypominany
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    /**
     * Tworzenie nowej instancji klasy event
     * wymagane do obslugi JAXB
     */
    public Event() {
        alarm = false;
    }

    /**
     * Tworzenie nowej instancji klasy event (tworzenie wydarzenia)
     *
     * @param id        Id wydarzenia
     * @param title     Tytul wydarzenia
     * @param place     Miejsce wydarzenia
     * @param startDate Data rozpoczecia wydarzenia
     * @param endDate   Data zakonczenia wydarzenia
     */
    public Event(int id, String title, String place, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        alarm = false;
    }

    /*
      Gettery i settery
     */

    /**
     * Zwraca id wydarzenia
     *
     * @return id wydarzenia
     */
    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Ustawia id wydarzenia
     *
     * @param id numer wydarzenia do przypisania
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Zwraca tytul wydarzenia
     *
     * @return tytul wydarzenia
     */
    @XmlAttribute(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * Ustawia tytul wydarzenia
     *
     * @param title Tytul wydarzenia do przypisania
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Zwraca miejsce wydarzenia
     *
     * @return miejsce wydarzenia
     */
    @XmlAttribute(name = "place")
    public String getPlace() {
        return place;
    }

    /**
     * Ustawia miejsce wydarzenia
     *
     * @param place Miejsce wydarzenia do przypisania
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Zwraca date rozpoczecia wydarzenia
     *
     * @return zwraca date w formacie Timestamp [ms]
     */
    @XmlTransient
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * Pozwala na zwrocenie daty wydarzenia w postaci stringa
     * Potrzebne do JAXB
     *
     * @return Data rozpoczecia wydarzenia w postaci stringa
     */
    @XmlAttribute(name = "startDate")
    public String getStartDateToXml() {
        return startDate.toString();
    }

    /**
     * Ustawia date poczatkowa wydarzenia
     *
     * @param startDate data poczatkowa wydarzenia do ustawienia
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * Ustawia date rozpoczecia wydarzenia
     * Potrzebne do wczytywanie danych z XMLA
     *
     * @param startDate data w formacie "dd/mm/yyyy"
     */
    public void setStartDateToXml(String startDate) {
        this.startDate = Timestamp.valueOf(startDate);
    }

    /**
     * Zwraca date zakonczenia wydarzenia
     *
     * @return data zakonczenia wydarzenia
     */
    @XmlTransient
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * Pozwala na zwrocenie daty wydarzenia zakoczenia w postaci stringa
     * Potrzebne do JAXB
     *
     * @return Data zakonczenia wydarzenia w postaci stringa
     */
    @XmlAttribute(name = "endDate")
    public String getEndDateToXml() {
        return endDate.toString();
    }

    /**
     * Ustawia date zakoczenia wydarzenia
     * Potrzebne do wczytywanie danych z XMLA
     *
     * @param endDate data w formacie "dd/mm/yyyy"
     */
    public void setEndDateToXml(String endDate) {
        this.endDate = Timestamp.valueOf(endDate);
    }


    /**
     * Ustawia date zakonczenia wydarzenia
     *
     * @param endDate Data zakonczenia wydarzenia
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Zwraca zmienna informujaca o alarmie
     *
     * @return zwraca flage czy zmienna juz sie przypomniala
     */
    @XmlAttribute(name = "alarm")
    public boolean isAlarm() {
        return alarm;
    }


    /**
     * toString zwracajacy opis wydarzenia
     *
     * @return Zwraca opis wydarzenia
     */
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
