package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="events")
public class DataRepository {
    @XmlElements(@XmlElement(name="event"))
    private ArrayList<Event> events;

    public DataRepository()
    {
        setEvents(new ArrayList<Event>());
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
     * Zwraca wszystkie wydarzenia
     * @return Lista wydarze�
     */
    public ArrayList<Event> getAllEvents(){
        return events;
    }
    public int size()
    {
        return getAllEvents().size();
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "DataRepository{" +
                "events=" + getAllEvents() +
                '}';
    }


}
