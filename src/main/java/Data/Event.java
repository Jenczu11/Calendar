package Data;

import java.util.Date;
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
    private Date startDate;
    /**
     * Data zakonczenia wydarzenia
     */
    private Date endDate;
    /**
     * Zmienna informujaca, czy wystapi� ju� alarm przypisany do danego wydarzenia
     */
    public boolean alarmed;
}
