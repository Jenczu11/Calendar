package service;

import data.DataRepository;
import data.Event;
import exceptions.idException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class DataService {
    private static DataService ourInstance;

    public static DataService getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataService();
        }
        return ourInstance;
    }
    private DataRepository repository;
    private DataService() {
        repository=new DataRepository();
    }


    /**
     * Dodaje wydarzenie o podanych parametrach do repozytorium
     * @param id Id wydarzenia
     * @param title Tytul wydarzenia
     * @param description Opis wydarzenia
     * @param startDate Data rozpoczecia wydarzenia
     * @param endDate Data zakonczenia wydarzenia
     * @throws Exception Jezeli juz istnieje zdarzenie o podanym id,
     *  lub jezli data zakonczenia zdarzenia jest wczesniejsza od daty rozpoczecia zdarzenia
     */
    public void addEvent(String id,String title, String description, Timestamp startDate, Timestamp endDate) throws Exception {
        //TODO: Autonumeracja elementow czyli bez id
        for (Event event : repository.getAllEvents()) {
            if(event.getId()==Integer.parseInt(id))
                throw new idException("Zdarzenie o takim id juz istnieje");
            if(startDate.getTime()>endDate.getTime())
                throw new Exception("Data zakonczenia zdarzenia nie moze byc wczesniejsza od rozpoczecia");
        }
        repository.addEvent(new Event(Integer.parseInt(id), title, description, startDate, endDate));
    }
    /**
     * Edytuje wydarzenie o podanych parametrach
     * @param id Id wydarzenia
     * @param title Tytul wydarzenia
     * @param description Opis wydarzenia
     * @param startDate Data rozpoczecia wydarzenia
     * @param endDate Data zakonczenia wydarzenia
     * @throws Exception Kiedy wydarzenie o podanym id nie istnieje
     */
    public void editEvent(String id, String title, String description, Timestamp startDate, Timestamp endDate) throws Exception {
        int idInt=Integer.parseInt(id);
        ArrayList<Event> events= repository.getAllEvents();
        for (int i=0;i<events.size();i++) {
            if(events.get(i).getId()==idInt) {
                repository.editEvent(i, new Event(idInt, title, description, startDate, endDate));
                return;
            }
        }
        throw new Exception("Zdarzenie nie istnieje");
    }
    /**
     * Usuwa wydarzenie o podanym id z repozytorium
     * @param id Id wydarzenia
     * @throws Exception Kiedy nie istnieje wydarzenie o podanym id
     */
    public void removeEvent(String id) throws Exception {
        int idInt = Integer.parseInt(id);
        for (Event event : repository.getAllEvents()) {
            if (event.getId() == idInt) {
                repository.removeEvent(event);
                return;
            }
        }
        throw new idException("Zdarzenie nie istnieje");
    }
        /**
         * Usuwa wydarzenie o podanym id z repozytorium
         * @param id Id wydarzenia
         * @throws Exception Kiedy nie istnieje wydarzenie o podanym id
         */
        public void removeEvent(int id) throws Exception {
            for (Event event : repository.getAllEvents()) {
                if (event.getId() == id) {
                    repository.removeEvent(event);
                    return;
                }
            }
            throw new idException("Zdarzenie nie istnieje");
        }
    /**
     * Zwraca wszystkie eventy dla danej daty
     * @param stamp Data dla ktorej sa szukane Zdarzenia
     * @return Lista zdarzen dla danej daty
     */
     public List<Event> GetAllEventsForDate(Timestamp stamp){
        List<Event> Todays = new ArrayList<>();
        for(int i=0;i<repository.size();i++) {
            long timestamp = repository.getAllEvents().get(i).getStartDate().getTime();
            Calendar oneFromAll = Calendar.getInstance();
            oneFromAll.setTimeInMillis(timestamp);

            Calendar selected= Calendar.getInstance();
            selected.setTime(stamp);

            if(oneFromAll.get(Calendar.MONTH)==selected.get(Calendar.MONTH) && oneFromAll.get(Calendar.YEAR)==selected.get(Calendar.YEAR) && oneFromAll.get(Calendar.DAY_OF_MONTH)==selected.get(Calendar.DAY_OF_MONTH))
            {
                Todays.add(repository.getAllEvents().get(i));
            }
        }
        return Todays;
    }

    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy na Timestamp
     * @param ddMMyyyy String do parsownia
     * @return Zparsowany string na timestamp
     */
     public Timestamp StringToTimestamp(String ddMMyyyy) {
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
     public Timestamp StringToTimestampWithTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(parsedDate.getTime());
    }
    public int size()
    {
        return repository.size();
    }
    @Override
    //TODO: naprawic to String zeby jakos lepiej wyswietlal co nie ? albo jakies inne formaty toString
    public String toString() {
        return "DataService{" +
                "repository=" + repository +
                '}';
    }
}
