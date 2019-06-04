package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

/**
 * Klasa reprezentujaca repozytorium wydarzen
 */
@XmlRootElement(name="events")
public class DataRepository {
    /**
     * ArrayList zawierajacy wydarzenia
     */
    @XmlElements(@XmlElement(name="event"))
    private ArrayList<Event> events;
    /**
     * ID ostatniego eventu w bazie
     */
    @XmlTransient
    private static int lastEventID=0;
    public DataRepository()
    {
        setEvents(new ArrayList<>());
    }
    public void addEvent(Event e)
    {
        getAllEvents().add(e);
    }
    public Event getEvent(int index) {
        return getAllEvents().get(index);
    }
    public void removeEvent(Event e) {

        getAllEvents().remove(e);
    }

    /**
     * 
     * @param index
     */
    public void removeEvent(int index) {
        getAllEvents().remove(index);
    }
    public void set(int index, Event e) {
        getAllEvents().set(index, e);
    }
    public void editEvent(int index, Event event) {
        getAllEvents().set(index, event);
    }
    /**
     * Ustawia zmienna lastEventID na ostatni event w bazie danych
     */
    public void refreshLastEventId() {
        if (events.isEmpty()) lastEventID = 0;
        else {
            Event e = events.get(events.size() - 1);
            lastEventID = e.getId();
        }
    }

    static int getLastEventID() {
        return lastEventID;
    }

    public static void setLastEventID(int lastEventID) {
        DataRepository.lastEventID = lastEventID;
    }

    public ArrayList<Event> getAllEvents(){
        return events;
    }
    public int size()
    {
        return getAllEvents().size();
    }
    private void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
//    /**
//     * ładuje wydarzenia do events
//     * @param handler Interfejs obsługujacy zapis i odczyt z repozytorium
//     * @throws Exception Wyjatek przekazany z innej metody
//     */
//    public void setData(IOHandler handler) throws Exception {
//        this.events=handler.LoadData();
//    }
//    /**
//     * Zapisuje wydarzenia z evebts
//     * @param handler Interfejs obslugujacy zapis i odczyt z repozytorium
//     * @throws Exception Wyjatek przekazany z innej metody
//     */
//    public void saveData(IOHandler handler) throws Exception {
//        handler.SaveData(events);
//    }
    @Override
    public String toString() {
        return "DataRepository{" +
                "events=" + getAllEvents() +
                '}';
    }


}
