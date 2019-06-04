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
        // Mozna tez szukac Maxa w tablicy i zwiekszac o 1 bylo by najlepiej
        //
        //Bezpieczniej moze byc poprostu branie tablicy ale to do zmiany po konsultacjach
        //

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

    /**
     * Ustawia miejsce wydarzenia
     * @param place Ustaw miejsce wydarzenia
     * @return EventBuilder
     */
    public EventBuilder setPlace(String place) {
        if (place.isEmpty())
            place = "Default Place";
        this.place = place;
        return this;
    }

    /**
     * Ustawia date rozpoczecia wydarzenia
     * @param startDate Data rozpoczecia wydarzenia jako timeStamp
     * @return EventBuilder
     */
    public EventBuilder setStartDate(Timestamp startDate) {

        this.startDate = startDate;
        return this;
    }

    /**
     * Ustawia date zakonczenia wydarzenia
     * @param endDate Data zakonczenia wydarzenia jako TimeStamp
     * @return EventBuilder
     */
    public EventBuilder setEndDate(Timestamp endDate) {

        this.endDate = endDate;
        return this;
    }

    /**
     * Ustawia flage czy nalezy zaalarmowac o wydarzeniu
     * @param alarm Czy nalezy zaalarmowac o wydarzeniu
     * @return EventBuilder
     */
    public EventBuilder setAlarm(boolean alarm) {
        this.alarm = alarm;
        return this;
    }

    /**
     * Tworzy nowe instancje obiektu Event
     * @return nowa instacje obiektu Event z jego parametrami
     */
    public Event createEvent() {
        return new Event(id, title, place, startDate, endDate);
    }

    /**
     * Zwraca dane zapisana w eventBuilderze
     * @return zwraca opis wydarzenia
     */
    @Override
    public String toString() {
        return "Event|Builder{" +
                "id=" + id +
                ", Tyt='" + title + '\'' +
                ", Des='" + place + '\'' +
                ", start=" + startDate +
                ", end=" + endDate +
                ", alarm=" + alarm +
                '}';
    }
}