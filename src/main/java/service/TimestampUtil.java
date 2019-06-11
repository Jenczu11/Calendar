package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa pomocnicza do formatowania TimeStampow
 *
 */
public final class TimestampUtil {
    /**
     * Funkcja pomocnicza, parsuje string w formacie dd/MM/yyyy na Timestamp
     * @param ddMMyyyy String do parsownia
     * @return Zparsowany string na timestamp
     */
   public static Timestamp StringToTimestamp(String ddMMyyyy) {
        if (ddMMyyyy.isEmpty())
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

    /**
     * Funkcja pomocnicza, parsuje inty Timestamp
     * @param day dzien
     * @param month miesiac
     * @param year rok
     * @param hour godzina
     * @param minutes minuta
     * @return Sprasowany timestamp
     */
    public static Timestamp intsToTimestamp(int day,int month,int year,int hour,int minutes)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date parsedDate = null;
        String time = day+"/"+month+"/"+year+" "+hour+":"+minutes;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Blad w parsowaniu");
        }
        assert parsedDate != null: "Parsed date is null";
        return new Timestamp(parsedDate.getTime());
    }

    /**
     * Funkcja pomocnicza parsuje 3 inty i defaultowa godzina 00:00 na timeStamp
     * @param day dzien
     * @param month miesiac
     * @param year rok
     * @return timestamp o okreslonej dacie z godzina na 00:00
     */
    public static Timestamp intsToTimestamp(int day,int month,int year)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date parsedDate = null;
        String time = day+"/"+month+"/"+year+" "+"00:00";
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Blad w parsowaniu");
        }
        assert parsedDate != null: "Parsed date is null";
        return new Timestamp(parsedDate.getTime());
    }

}
