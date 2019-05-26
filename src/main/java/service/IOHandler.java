package service;

import data.DataRepository;
import data.Event;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public interface IOHandler {
    /**
     * Laduje dane do programu w wybrany sposob (XML)
     * @return data, zawierajace liste wydarzen (object type DataRepository)
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    public DataRepository LoadData() throws Exception;
    /**
     * Zapisuje dane w wybrany sposob (XML, SQL)
     * @param data , zawierajace liste wydarzen
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    public void SaveData(DataRepository data) throws Exception;
}
