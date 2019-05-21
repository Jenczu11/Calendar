package Data;

import java.util.ArrayList;

public class EventList {
    public ArrayList<Event> events = new ArrayList<Event>();

    public void add(Event e)
    {
        events.add(e);
    }
    public void remove(Event e)
    {
        events.remove(e);
    }
    public void remove(int index)
    {
        events.remove(index);
    }
    public void set(int index,Event e)
    {
        events.set(index,e);
    }
}
