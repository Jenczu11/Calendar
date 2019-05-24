import service.DataService;

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
                        System.out.println("");
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
