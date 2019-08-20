package data;

/**
 * Klasa statyczna dodajaca wlasne wysylanie danych na konsole
 */
public final class Utils {
    /**
     * Wyslanie na konsole [INFO]
     * @param str tekst do wyslania
     */
        public static void pInfo(String str) {
        System.out.println("[INFO] " + str);
    }
    /**
     * Wyslanie na konsole [DEBUG]
     * @param str tekst do wyslania
     */
    public static void pDebug(String str){
        System.out.println("[DEBUG] "+ str);
    }

}
