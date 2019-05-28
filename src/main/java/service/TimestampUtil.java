package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Klasa pomocnicza do formatowania TimeStampow
 *
 */
public class TimestampUtil {
    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy na Timestamp
     * @param ddMMyyyy String do parsownia
     * @return Zparsowany string na timestamp
     */
    public static Timestamp StringToTimestamp(String ddMMyyyy) {
        if (ddMMyyyy.isBlank())
            ddMMyyyy="24/05/2019";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(ddMMyyyy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert parsedDate != null :"Parsed date is null";
        return new Timestamp(parsedDate.getTime());
    }
    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy HH:mm na Timestamp
     * @param time String do parsownia
     * @return Zparsowany string na timestamp
     */

    public static Timestamp StringToTimestampWithTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert parsedDate != null: "Parsed date is null";
        return new Timestamp(parsedDate.getTime());
    }
}
