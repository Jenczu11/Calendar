import service.DataService;

import java.sql.Timestamp;
import java.util.Scanner;

public class program {
    public static void showMenu()
    {
        System.out.println("--------------------------------");
        System.out.println("-                              -");
        System.out.println("-     Calendar                 -");
        System.out.println("-    a - dodaj event           -");
        System.out.println("-    s - wyswietl wydarzenia   -");
        System.out.println("-                              -");
        System.out.println("-    m - pokaz menu            -");
        System.out.println("-    q - zakoncz program       -");
        System.out.println("--------------------------------");
    }
    public static void main(String[] args) {
        System.out.println("s");
        System.out.println("test");
        System.out.println("Julson");
        System.out.println("Tworze dataService");
        DataService dataService = DataService.getInstance();
        char choice;
        if(args[0].equals("-cli"))
            System.out.println("cli");
        showMenu();
            for(;;) {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.next().charAt(0);
                switch (choice) {
                    case 'a':
                        System.out.println("Podaj id");
                        String id = scanner.nextLine();
                        System.out.println("Podaj tytul wydarzenia");
                        String title = scanner.nextLine();
                        System.out.println("Podaj opis wydarzenia");
                        String description=scanner.nextLine();
                        System.out.println("Podaj date poczatkowa w formacie dd/MM/yyyy");
                        String startDateString = scanner.nextLine();
                        Timestamp startDate = dataService.StringToTimestamp(startDateString);
                        System.out.println("Podaj date koncowa w formacie dd/MM/yyyy");
                        String EndDateString = scanner.nextLine();
                        Timestamp endDate = dataService.StringToTimestamp(EndDateString);
                        try {
                            dataService.addEvent(id,title,description,startDate,endDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 's':
                        System.out.println("Ilosc wydarzen w bazie"+dataService.size());
                    case 'q':
                        return;
                    case 'm':
                        showMenu();
                        break;
                    default:
                        break;
                }
            }

    }
}
