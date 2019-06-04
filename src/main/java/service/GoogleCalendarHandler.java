package service;

import data.DataRepository;
import data.Event;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Klasa odpowiedzialna za zapis do formatu .csv (obslugiwanego przez Gcal)
 */
public class GoogleCalendarHandler implements IOHandler  {
    /**
     * Nazwa pliku, do ktorego zostaja zapisane dane
     */
    private String filename;
    /**
     * Ustawia nazwe pliku, do ktorego zostana zapisane dane
     * @param filename Nazwa pliku
     */
    public void setFilename(String filename) {
        this.filename=filename;
    }

    public GoogleCalendarHandler(String filename) {
        if(filename.isEmpty())
            filename="ExportGCalendar";
        filename+=".csv";
        this.filename = filename;
    }

    /**
     * Laduje dane z plikow csv, nie zaimplementowane i nie wymagane
     * @return
     * @throws Exception
     */
    @Override
    public DataRepository LoadData() throws Exception {
        throw new UnsupportedOperationException("not implemented yet");
    }

    /**
     * Zapisuje pliki do pliku csv okreslonego konstruktorem/lub setterem
     * @param data , zawierajace liste wydarzen
     * @throws Exception
     */
    @Override
    public void SaveData(DataRepository data) throws Exception {
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();
        sb.append("Subject");
        sb.append(',');
        sb.append("Start Date");
        sb.append(',');
        sb.append("Start Time");
        sb.append(',');
        sb.append("End Date");
        sb.append(',');
        sb.append("End Time");
        sb.append(',');
        sb.append("Place");
        sb.append('\n');
        for (Event event : data.getAllEvents()) {
            sb.append(event.getTitle());
            sb.append(',');
            sb.append(new SimpleDateFormat("MM/dd/yyyy").format(event.getStartDate()));
            sb.append(',');
            sb.append(new SimpleDateFormat("KK:mm aa",Locale.ENGLISH).format(event.getStartDate()));
            sb.append(',');
            sb.append(new SimpleDateFormat("MM/dd/yyyy").format(event.getEndDate()));
            sb.append(',');
            sb.append(new SimpleDateFormat("KK:mm aa", Locale.ENGLISH).format(event.getEndDate()));
            sb.append(',');
            sb.append(event.getPlace());
            sb.append('\n');
        }
        pw.write(sb.toString());
        pw.close();

    }
}
