package service;

import data.DataRepository;
import data.Event;
import data.EventBuilder;
import exceptions.idException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    void addEvent(String id, String title, String description, Timestamp startDate, Timestamp endDate) throws Exception {
        //TODO: Autonumeracja elementow czyli bez id
        for (Event event : repository.getAllEvents()) {
            if(event.getId()==Integer.parseInt(id))
                throw new idException("Zdarzenie o takim id juz istnieje");
            if(startDate.getTime()>endDate.getTime())
                throw new Exception("Data zakonczenia zdarzenia nie moze byc wczesniejsza od rozpoczecia");
        }
        //repository.addEvent(new Event(Integer.parseInt(id),title,description,startDate,endDate));
        repository.addEvent(new EventBuilder().setId(Integer.parseInt(id)).setTitle(title).setDescription(description).setStartDate(startDate).setEndDate(endDate).createEvent());
        refreshID();
    }
    public void addEvent(Event e) throws Exception
    {
        for(Event event: repository.getAllEvents())
        {
            if(event.getId()==e.getId())
                throw new idException("Zdarzenie o takim id juz istnieje");
            if(e.getStartDate().getTime()>e.getEndDate().getTime())
                throw new Exception("Data zakonczenia zdarzenia nie moze byc wczesniejsza od rozpoczecia");
        }
        repository.addEvent(e);
        refreshID();
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
                repository.editEvent(i, new EventBuilder().setId(idInt).setTitle(title).setDescription(description).setStartDate(startDate).setEndDate(endDate).createEvent());
                refreshID();
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
    void removeEvent(String id) throws Exception {
        int idInt = Integer.parseInt(id);
        for (Event event : repository.getAllEvents()) {
            if (event.getId() == idInt) {
                repository.removeEvent(event);
                refreshID();
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
        void removeEvent(int id) throws Exception {
            for (Event event : repository.getAllEvents()) {
                if (event.getId() == id) {
                    repository.removeEvent(event);
                    refreshID();
                    return;
                }
            }
            throw new idException("Zdarzenie nie istnieje");
        }

    /**
     * Usuwa wsystkie wydarzenia do podanej daty w formacie dd/mm/yyyy HH:MM
     * Liczy diff pomiedzy czasem podanym a czasem eventu, jezeli jest ujemny to mozemy
     * dodac event do chwilowej bazy ktory potem przypisujemy
     * Troche za bardzo mem-hungry ale cóż
     * @param deleteTo data w formacie dd/mm/yyyy HH: (Timestamp.valueOf(""))
     * @throws Exception
     */
        void removeEventsToDate(Timestamp deleteTo) throws Exception{
            int startSize=repository.getAllEvents().size();
            if(startSize==0) throw new Exception("Pusta baza eventow");
            DataRepository temp = new DataRepository();
            for(int i=0; i<startSize;i++) {
               long diff=deleteTo.getTime()-repository.getEvent(i).getStartDate().getTime();
               if(diff<0)
               {
                   temp.getAllEvents().add(repository.getEvent(i));
               }
            }
           setRepository(temp);
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

            if(oneFromAll.get(Calendar.MONTH)==selected.get(Calendar.MONTH) && 
            		oneFromAll.get(Calendar.YEAR)==selected.get(Calendar.YEAR) && 
            		oneFromAll.get(Calendar.DAY_OF_MONTH)==selected.get(Calendar.DAY_OF_MONTH))
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
         return TimestampUtil.StringToTimestamp(ddMMyyyy);
     }

    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy HH:mm na Timestamp
     * @param time String do parsownia
     * @return Zparsowany string na timestamp
     */
     public Timestamp StringToTimestampWithTime(String time) {
         return TimestampUtil.StringToTimestampWithTime(time);
     }
    public int size()
    {
        return repository.size();
    }

    List<Event> getRepositoryEvents() {
        return repository.getAllEvents();
    }

    /**
     * Odswieza pole LastEventID w dataRepository
     * Razem z EventBuilder setId powoduje inkrementacje od ostatniego Event ID+1
     */
    void refreshLastEventID()
    {
        if (repository.getAllEvents().isEmpty()) DataRepository.setLastEventID(0);
        else {
            Event lastEvent = repository.getAllEvents().get(repository.getAllEvents().size() - 1);
            DataRepository.setLastEventID(lastEvent.getId());
        }
    }

    /**
     * Odswieza pole LastEventID w dataRepository
     * Metoda szuka maksymalnej wartości ID w bazie danych i zwiększa go o 1
     */
    public void refreshID()
    {
        if (repository.getAllEvents().isEmpty()) DataRepository.setLastEventID(0);
        if (repository.getAllEvents().size()==1) DataRepository.setLastEventID(1);
        else {

            for (int i = 0 ;i<repository.getAllEvents().size()-1;i++)
                DataRepository.setLastEventID(Math.max(repository.getEvent(i).getId(),repository.getEvent(i+1).getId()));

        }
    }

    public ArrayList<Event> getAllEvents()
    {
        return repository.getAllEvents();
    }
    public DataRepository getRepository() {
        return repository;
    }

    public void setRepository(DataRepository repository) {
        this.repository = repository;
        refreshID();
    }

    /**
    Ładuje repozytorium za pomocą napisanego handlera (XML,SQL)
     */
    public void loadRepository(IOHandler handler) throws Exception
    {
        setRepository(handler.LoadData());
        refreshID();
    }
    public void saveRepository(IOHandler handler) throws Exception
    {
       handler.SaveData(getRepository());
    }

    @Override
    //TODO: naprawic to String zeby jakos lepiej wyswietlal co nie ? albo jakies inne formaty toString
    public String toString() {
        return "DataService{" +
                "repository=" + repository +
                '}';
    }
}
