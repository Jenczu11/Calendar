package service;

import data.DataRepository;
import data.Event;

import java.util.ArrayList;
import java.util.List;

public interface IOHandler {
    /**
     * Laduje dane do programu w wybrany sposob (XML, SQL)
     * @return data, zawierajace liste wydarzen
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    public ArrayList<Event> LoadData() throws Exception;
    /**
     * Zapisuje dane w wybrany sposob (XML, SQL, Google Calendar)
     * @param data DataContext, zawierajace liste wydarzen
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    public void SaveData(ArrayList<Event> data) throws Exception;
}
