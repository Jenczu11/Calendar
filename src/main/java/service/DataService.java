package service;

import data.DataRepository;
import data.Event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DataService {
    private static DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }
    private DataRepository repository;
    private DataService() {
        repository=new DataRepository();
    }


    /**
     * Dodaje wydarzenie o podanych parametrach do repozytorium
     * @param id Id wydarzenia
     * @param title Tytu� wydarzenia
     * @param description Opis wydarzenia
     * @param startDate Data rozpocz�cia wydarzenia
     * @param endDate Data zako�czenia wydarzenia
     * @throws Exception Je�li ju� istnieje zdarzenie o podanym id,
     *  lub je�li data zako�czenia zdarzenia jest wcze�niejsza od daty rozpocz�cia zdarzenia
     */
    public void addEvent(String id,String title, String description, Timestamp startDate, Timestamp endDate) throws Exception {
        for (Event event : repository.getAllEvents()) {
            if(event.getId()==Integer.parseInt(id))
                throw new Exception("Zdarzenie o takim id ju� istnieje");
            if(startDate.getTime()>endDate.getTime())
                throw new Exception("Data zako�czenia zdarzenia nie mo�e by� wcze�niejsza od rozpocz�cia");
        }
        repository.addEvent(new Event(Integer.parseInt(id), title, description, startDate, endDate));
    }
    /**
     * Edytuje wydarzenie o podanych parametrach
     * @param id Id wydarzenia
     * @param title Tytu� wydarzenia
     * @param description Opis wydarzenia
     * @param startDate Data rozpocz�cia wydarzenia
     * @param endDate Data zako�czenia wydarzenia
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
        int idInt=Integer.parseInt(id);
        for (Event event : repository.getAllEvents()) {
            if(event.getId()==idInt) {
                repository.removeEvent(event);
                return;
            }
        }
        throw new Exception("Zdarzenie nie istnieje");
    }

    @Override
    public String toString() {
        return "DataService{" +
                "repository=" + repository +
                '}';
    }
}
