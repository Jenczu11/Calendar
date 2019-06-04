package data;

import java.sql.Timestamp;

/**
 * Klasa implementujaca wzorzec projektowy budowniczego
 * Pozwala na tworzenie eventow z ich wartosciami domyslnymi
 */
public class EventBuilder {

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
     * Ustawia id wydarzenia
     * @param id Id wydarzenia
     * @return EventBuilder
     */
    public EventBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Ustawia id Wydazrenia (dziala jako autonumeracja)
     * @param idValue Id wydarzenia w postaci stringa ktore jest odpowiednio konwertowane
     * @return EventBuilder
     */
    public EventBuilder setId(String idValue) {
        //TODO: nad tym trzeba popracowac bo funkcja zle zadziala kiedy posortujemy dane i bedziemy chcieli wziac ostatnie ID
        //
        //Bezpieczniej moze byc poprostu branie tablicy ale to do zmiany po konsultacjach

        if (idValue.isEmpty()) {
            //            idValue=Integer.toString((int)Math.random()*100);
            this.id = DataRepository.getLastEventID() + 1;
        } else this.id = Integer.parseInt(idValue);
        return this;
    }

    /**
     * Ustawia tytul wydarzeenia
     * @param title Tytul wydarzenia w stringu
     * @return EventBuilder
     */
    public EventBuilder setTitle(String title) {
        if (title.isEmpty())
            title = "Default Title";
        this.title = title;
        return this;
    }

    public EventBuilder setPlace(String place) {
        if (place.isEmpty())
            place = "Default Place";
        this.place = place;
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

    public EventBuilder setAlarm(boolean alarm) {
        this.alarm = alarm;
        return this;
    }

    public Event createEvent() {
        return new Event(id, title, place, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Wydarzenie{" +
                "id=" + id +
                ", Tyt='" + title + '\'' +
                ", Des='" + place + '\'' +
                ", start=" + startDate +
                ", end=" + endDate +
                ", alarm=" + alarm +
                '}';
    }
}