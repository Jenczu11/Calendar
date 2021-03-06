package data;

import data.DataRepository;

/**
 * Interfejs do obslugi handlerow
 */
public interface IOHandler {
    /**
     * Laduje dane do programu w wybrany sposob (XML,SQL)
     * @return data, zawierajace liste wydarzen (object type DataRepository)
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    DataRepository LoadData() throws Exception;
    /**
     * Zapisuje dane w wybrany sposob (XML, SQL)
     * @param data , zawierajace liste wydarzen
     * @throws Exception Wyjatek zwiazany z obsluga IO
     */
    void SaveData(DataRepository data) throws Exception;
}
