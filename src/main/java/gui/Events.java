package gui;

import data.EventBuilder;
import exceptions.dataException;
import exceptions.idException;
import service.DataService;
import service.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;

//import net.miginfocom.swing.MigLayout;


/**
 * 
 * Klasa umozliwiajaca dodanie wydarzenia
 *
 */
public class Events {

    private static DayView dayView;
    private static DataService dService;
    private static JFrame frame;
    private static Timestamp date;
    private static Button button;
    private static Label label;
    private static DefaultTableModel model = null;

    /**
     * Launch the application.
     * Nalezy odkomentowac konstruktor aby moc uruchomic okienko bezposrednio
     * @param args set to null
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Events window = new Events(date,model,dayView);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public Events(Timestamp date, DefaultTableModel model, DayView dw) {

        Events.date = date;
        this.model = model;
        dayView=dw;
        dService = DataService.getInstance();
        initialize();
//		this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private static void initialize() {

        //<editor-fold desc="JFrame init+setup">
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(192, 192, 192));
        frame.setBounds(100, 100, 347, 275);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //</editor-fold>

        //<editor-fold desc="JLabel Nazwa setup">
        JLabel lblNazwa = new JLabel("Nazwa");
        lblNazwa.setForeground(Color.WHITE);
        lblNazwa.setBackground(Color.WHITE);
        lblNazwa.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNazwa.setBounds(12, 50, 56, 16);
        frame.getContentPane().add(lblNazwa);
        //</editor-fold>

        //<editor-fold desc="JLabel miejsce setup">
        JLabel lblMiejsce = new JLabel("Miejsce");
        lblMiejsce.setForeground(Color.WHITE);
        lblMiejsce.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblMiejsce.setBounds(12, 79, 56, 16);
        frame.getContentPane().add(lblMiejsce);
        //</editor-fold>

        //<editor-fold desc="JLabel godzina rozpoczecia setup">
        JLabel lblGodzinaRozpoczecia = new JLabel("Godzina rozpocz\u0119cia");
        lblGodzinaRozpoczecia.setForeground(Color.WHITE);
        lblGodzinaRozpoczecia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGodzinaRozpoczecia.setBounds(12, 123, 155, 16);
        frame.getContentPane().add(lblGodzinaRozpoczecia);
        //</editor-fold>

        //<editor-fold desc="JLabel Godzina zakonczenie setup">
        JLabel lblGodzinaZakonczenia = new JLabel("Godzina zako\u0144czenia");
        lblGodzinaZakonczenia.setForeground(Color.WHITE);
        lblGodzinaZakonczenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGodzinaZakonczenia.setBounds(12, 152, 146, 16);
        frame.getContentPane().add(lblGodzinaZakonczenia);
        //</editor-fold>

        //<editor-fold desc="Checkbox Powiadomienie">
        Checkbox checkbox = new Checkbox("Powiadomienie");
        checkbox.setForeground(Color.WHITE);
        checkbox.setBounds(10, 184, 108, 24);
        frame.getContentPane().add(checkbox);
        //</editor-fold>

        //<editor-fold desc="Label wydarzenie">

        label = new Label("Nowe wydarzenie:");
        label.setForeground(new Color(25, 25, 112));
        label.setFont(new Font("Dialog", Font.BOLD, 17));
        label.setBounds(65, 10, 196, 24);
        frame.getContentPane().add(label);
        //</editor-fold>

        //<editor-fold desc="Button Dodaj">
        button = new Button("DODAJ");

        button.setFont(new Font("Dialog", Font.BOLD, 14));
        button.setActionCommand("DODAJ");
        button.setBackground(new Color(25, 25, 112));
        button.setForeground(new Color(255, 215, 0));
        button.setBounds(170, 184, 120, 24);
        frame.getContentPane().add(button);
        //</editor-fold>

        //<editor-fold desc="JTextArea Tytul">
        JTextArea Title = new JTextArea();
        Title.setBounds(94, 53, 196, 16);
        frame.getContentPane().add(Title);
        //</editor-fold>

        //<editor-fold desc="JTextArea Place">
        JTextArea Place = new JTextArea();
        Place.setBounds(94, 83, 196, 16);
        frame.getContentPane().add(Place);
        //</editor-fold>

        //<editor-fold desc="Choice startDateHours">
        Choice startDateHours = new Choice();
        startDateHours.setBounds(172, 117, 48, 22);
        addToChoiceNumbers(startDateHours,0,24);
        frame.getContentPane().add(startDateHours);
        //</editor-fold>

        //<editor-fold desc="Choice endDateHours">
        Choice endDateHours = new Choice();
        endDateHours.setBounds(172, 146, 48, 22);
        addToChoiceNumbers(endDateHours,0,24);
        frame.getContentPane().add(endDateHours);
        //</editor-fold>

        //<editor-fold desc="Choice startDateMinutes">
        Choice startDateMinutes = new Choice();
        startDateMinutes.setBounds(242, 117, 48, 22);
        addToChoiceNumbers(startDateMinutes,0,60);
        frame.getContentPane().add(startDateMinutes);
        //</editor-fold>

        //<editor-fold desc="Choice endDateMinutes">
        Choice endDateMinutes = new Choice();
        endDateMinutes.setBounds(242, 146, 48, 22);
        addToChoiceNumbers(endDateMinutes,0,60);
        frame.getContentPane().add(endDateMinutes);
        //</editor-fold>

        /*
          Action listnery
         */
        button.addActionListener(e -> {
            EventBuilder builder = new EventBuilder();
            builder.setId("");
            builder.setTitle(Title.getText());
            builder.setPlace(Place.getText());
            String time = startDateHours.getSelectedItem() + ":" + startDateMinutes.getSelectedItem() + ":00";
            builder.setStartDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
            time = endDateHours.getSelectedItem() + ":" + endDateMinutes.getSelectedItem() + ":00";
            builder.setEndDate(Timestamp.valueOf(date.toString().substring(0, 10) + " " + time));
            builder.setAlarm(checkbox.getState());
            try {
                dService.addEvent(builder.createEvent());
                dayView.showEvents();
                frame.dispose();
                Utils.pInfo("Dodano " + builder.toString());
            } catch (idException | dataException ex) {

                System.err.println(ex.toString());
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Wystapil blad przy tworzeniu eventu",JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    /**
     * Dodaje pokolei liczby do choice w formacie %dd
     * Zakres od 00-99
     * @param where do jakiego componentu dodac
     * @param start od jakiego numeru ma tworzyc
     * @param end do jakiego numeru ma tworzyc
     */
    private static void addToChoiceNumbers(Choice where, int start, int end)
    {
        if(end>100) end=99;
        for (int i = start; i < end; i++)
            if (i <= 9) {
                where.add("0" + String.format("%d", i));
            } else
                where.add(String.format("%d", i));
    }
}
