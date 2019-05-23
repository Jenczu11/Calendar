package data;

import java.util.ArrayList;

public class DataRepository {
    private ArrayList<Event> events;

    public DataRepository()
    {
        events = new ArrayList<Event>();
    }
    public void addEvent(Event e)
    {
        events.add(e);
    }
    public Event getEvent(int index) {
        return events.get(index);
    }
    public void removeEvent(Event e) {

        events.remove(e);
    }
    public void removeEvent(int index) {
        events.remove(index);
    }
    public void set(int index, Event e) {
        events.set(index, e);
    }
    public void editEvent(int index, Event event) {
        events.set(index, event);
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
        return events.size();
    }

    @Override
    public String toString() {
        return "DataRepository{" +
                "events=" + events +
                '}';
    }
}
