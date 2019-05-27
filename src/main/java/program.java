//TODO: Nalezy zrobic wszelkie zabezpieczenia przed wpisaniem null, do eventu tak samo jak i glupich danych

import data.EventBuilder;
import service.DataService;
import service.SQLHandler;
import service.XMLHandler;

import java.util.Scanner;

public class program {
    private static void showMenu()
    {
        System.out.println("--------------------------------");
        System.out.println("-                              -");
        System.out.println("-     Calendar                 -");
        System.out.println("-    a - dodaj event           -");
        System.out.println("-    p - wyswietl wydarzenia   -");
        System.out.println("-    s - zapisz wydarzenia     -");
        System.out.println("-    d - dodaj wydarzenia z xml-");
        System.out.println("-    m - pokaz menu            -");
        System.out.println("-    q - zakoncz program       -");
        System.out.println("--------------------------------");
    }
    //TODO: dodac wyswietlanie z daneago dnia mozliwosc
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
                            EventBuilder builder = new EventBuilder();
                            dataService.refreshLastEventID();
                            System.out.println("Podaj id");
                            scanner.nextLine();

                            builder.setId(scanner.nextLine());
                            System.out.println("Podaj tytul wydarzenia");
//                        String title = scanner.nextLine();
                            builder.setTitle(scanner.nextLine());
                            System.out.println("Podaj opis wydarzenia");
//                        String description=scanner.nextLine();
                            builder.setDescription(scanner.nextLine());
                            System.out.println("Podaj date poczatkowa w formacie dd/MM/yyyy");
//                        String startDateString = scanner.nextLine();
                            builder.setStartDate(dataService.StringToTimestamp(scanner.nextLine()));
//                        Timestamp startDate = dataService.StringToTimestamp(startDateString);
                            System.out.println("Podaj date koncowa w formacie dd/MM/yyyy");
//                        String EndDateString = scanner.nextLine();
                            builder.setEndDate(dataService.StringToTimestamp(scanner.nextLine()));
//                        Timestamp endDate = dataService.StringToTimestamp(EndDateString);

                            try {
                                dataService.addEvent(builder.createEvent());
                                System.out.println("Pomyslnie dodano wydarzenie");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        break;
                    case 'p':
                        System.out.println("Ilosc wydarzen w bazie"+dataService.size());
                        System.out.println(dataService.toString());
                        break;
                    case 's':
                        System.out.println("Zapisuje dane do XML");
//                        XMLHandlerNotWorking xmlHandler = new XMLHandlerNotWorking();
//                        try {
//                            xmlHandler.SaveData(dataService.getRepository().getAllEvents());
//                        } catch (Exception e) {
//
//                        }
                        XMLHandler xmlHandler = new XMLHandler("");
                        SQLHandler sqlHandler1 = new SQLHandler();
                        try {
                          dataService.saveRepository(sqlHandler1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 'd':
                        System.out.println("Wczytuje dane z XML");
//                        XMLHandlerNotWorking xmlHandler1 = new XMLHandlerNotWorking();
//                        try {
//                           dataService.getRepository().setEvents(xmlHandler1.LoadData());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        XMLHandler xmlHandlerv21 = new XMLHandler("");
                        SQLHandler sqlHandler = new SQLHandler();
                        try {
                            dataService.loadRepository(sqlHandler);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
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
