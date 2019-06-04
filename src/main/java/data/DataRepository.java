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

    /**
     * Konstruktor tworzacy repozytorium z danymi
     */
    public DataRepository()
    {
        setEvents(new ArrayList<>());
    }

    /**
     * Dodanie eventu do repozytorium
     * @param e event do dodania
     */
    public void addEvent(Event e)
    {
        getAllEvents().add(e);
    }

    /**
     * Zwraca wydarzenie o podanym indeksie
     * @param index index wydarzenia ktore chcemy pobrac
     * @return Event o podanym indeksie
     */
    public Event getEvent(int index) {
        return getAllEvents().get(index);
    }

    /**
     * Usuwa event z bazy
     * @param e Event do usuniecia o danych parametrach
     */
    public void removeEvent(Event e) {

        getAllEvents().remove(e);
    }

    /**
     * Usuwa event z bazy o danym ID
     * @param index Indeks wydarzenia do usuniecia
     */
    public void removeEvent(int index) {
        getAllEvents().remove(index);
    }

    /**
     * Ustawia event na danym miejscu w indeksie (nadpisuje)
     * @param index indeks eventu (gdzie ma wstawic)
     * @param event Event do wstawienia
     */
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

    /**
     * Zwraca ID ostatniego eventu
     * @return ID ostatniego eventu
     */
    static int getLastEventID() {
        return lastEventID;
    }

    /**
     * Ustawia id ostatniego eventu
     * @param lastEventID ID ostatniego eventu
     */
    public static void setLastEventID(int lastEventID) {
        DataRepository.lastEventID = lastEventID;
    }

    /**
     * Zwraca ArrayListe eventow
     * @return Zwraca ArrayListe Eventow
     */
    public ArrayList<Event> getAllEvents(){
        return events;
    }

    /**
     * Zwraca rozmiar repozytorium
     * @return rozmiar
     */
    public int size()
    {
        return getAllEvents().size();
    }

    /**
     * Ustawia repozytorium na dana liste
     * @param events ArrayLista eventow
     */
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
