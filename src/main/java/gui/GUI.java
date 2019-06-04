package gui;

import com.toedter.calendar.JCalendar;
import data.Event;
import service.DataService;
import service.SQLHandler;
import service.Utils;
import service.XMLHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;

public class GUI {
    private boolean onPanel = false;
    boolean pressedEnter = false;
    private int day;
    private int month;
    private int year;
    private JFrame frame;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    /**
     * Liczba milisekund, po ktorych nastepuje sprawdzanie czy jakies zdarzenia wymagaja zaalarmowania
     * Wartosc 60000 sprawdza co minute
     */
    private static final int TIMER_DELAY = 60000;
    /**
     * Glowny DataService
     */
    private final DataService dataService;

    /**
     * Launch the application.
     * @param args set to null
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the application.
     */
    private GUI() {
        initialize();
//        frame.setVisible(true);
        dataService = DataService.getInstance();
        try {
            dataService.loadRepository(new SQLHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkEvents();
            }
        });
        timer.start();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        //<editor-fold desc="Init frame with calendar panel">
        frame = new JFrame();
//        frame.addKeyListener(this);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JCalendar calendar = new JCalendar();
        calendar.getYearChooser().getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 13));
        calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 13));
        calendar.getDayChooser().getDayPanel().setBackground(new Color(255, 255, 255));

        frame.getContentPane().add(calendar, BorderLayout.CENTER);
        calendar.getDayChooser().setAlwaysFireDayProperty(true);
        //</editor-fold>

        //<editor-fold desc="Add MenuBar">
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(25, 25, 112));
        frame.setJMenuBar(menuBar);
        JMenu mnFile = new JMenu("File");
        mnFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mnFile.setForeground(new Color(255, 215, 0));
        menuBar.add(mnFile);
        JMenu mnXml = new JMenu("XML");
        mnFile.add(mnXml);
        JMenu mnSql = new JMenu("SQL");
        mnFile.add(mnSql);
        //</editor-fold>

        //<editor-fold desc="SaveToXML button">
        JMenuItem mntmSaveToXml = new JMenuItem("Save to XML");
        mntmSaveToXml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.saveRepository(new XMLHandler("XMLtest.xml"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Utils.pInfo("Zapisano do XML");
            }
        });
        mnXml.add(mntmSaveToXml);
        //</editor-fold>

        //<editor-fold desc="LoadToXML button">
        JMenuItem mntmLoadFromXml = new JMenuItem("Load from XML");
        mntmLoadFromXml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.loadRepository(new XMLHandler("XMLtest.xml"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Utils.pInfo("Zaladowane z XML");
            }
        });
        mnXml.add(mntmLoadFromXml);
        //</editor-fold>

        //<editor-fold desc="SaveToSQL button">
        JMenuItem mntmSaveToSql = new JMenuItem("Save to SQL");
        mntmSaveToSql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.saveRepository(new SQLHandler());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Utils.pInfo("Zapisano do SQL");
            }
        });
        mnSql.add(mntmSaveToSql);
        //</editor-fold>

        //<editor-fold desc="LoadFromSQL button">
        JMenuItem mntmLoadFromSql = new JMenuItem("Load from SQL");
        mntmLoadFromSql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dataService.loadRepository(new SQLHandler());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Utils.pInfo("Zaladowano z SQL");
            }
        });
        mnSql.add(mntmLoadFromSql);
        //</editor-fold>

        //<editor-fold desc="AboutUs Button">
        JMenu mnAboutus = new JMenu("About Us");
        mnAboutus.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mnAboutus.setForeground(new Color(255, 215, 0));
        mnAboutus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AboutUs aboutUs = new AboutUs();
                //TODO: mozna zmienic konstruktor zeby odpalal
                AboutUs.main(null);
            }
        });
        menuBar.add(mnAboutus);
        //</editor-fold>

        //<editor-fold desc="Settings button">
        JMenu mnSettings = new JMenu("Settings");
        mnSettings.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mnSettings.setForeground(new Color(255, 215, 0));
        mnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


            }
        });
        menuBar.add(mnSettings);
        //</editor-fold>

        //<editor-fold desc="ColorPicker Button">
        JMenuItem mntmColorpicker = new JMenuItem("ColorPicker");
        mntmColorpicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Another ColorPicker
                //Color choosenColor=JColorChooser.showDialog(null,"Wybor koloru",Color.GRAY);

                //<editor-fold desc="ColorPickerSetup only HSB">
                JColorChooser cc = new JColorChooser();
                AbstractColorChooserPanel[] panels = cc.getChooserPanels();
                JPanel p = new JPanel();
                panels[1].setBorder(
                        new TitledBorder(panels[1].getDisplayName()));
                p.add(panels[1]);
                JPanel gui = new JPanel(new BorderLayout(2, 2));
                gui.add(p, BorderLayout.CENTER);
                JOptionPane.showMessageDialog(null, gui);
                //</editor-fold>
                Color choosenColor = cc.getColor();
                calendar.getDayChooser().getDayPanel().setBackground(choosenColor.brighter());

            }
        });
        mnSettings.add(mntmColorpicker);

        JMenu mnDeleteEvents = new JMenu("Delete Events");
        mnDeleteEvents.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mnDeleteEvents.setForeground(new Color(255, 215, 0));
        mnDeleteEvents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Thread t = new Thread(new DeleteEvents(calendar.getMonthChooser().getMonth(), calendar.getYearChooser().getYear()));
                t.start();
//                DeleteEvents de = new DeleteEvents(calendar.getMonthChooser().getMonth(), calendar.getYearChooser().getYear());
//                de.main(null);

            }
        });

        menuBar.add(mnDeleteEvents);

        JMenu mnSearchEvents = new JMenu("Search Events");
        mnSearchEvents.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mnSearchEvents.setForeground(new Color(255, 215, 0));
        mnSearchEvents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SearchEvents searchEvents = new SearchEvents();
                SearchEvents.main(null);
            }
        });
        menuBar.add(mnSearchEvents);
        //</editor-fold>


        JPanel jPanel = calendar.getDayChooser().getDayPanel();
        Component[] component = jPanel.getComponents();
        for (int i = 0; i < component.length; i++) {
            component[i].addMouseListener(new MouseAdapter() {
                //<editor-fold desc="MouseListener zabezpieczenie przed zlym wyborem (do klawiatury)">
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    super.mouseEntered(mouseEvent);
                    onPanel = true;
//                        System.out.println("onPanel = " + onPanel);
                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    super.mouseExited(mouseEvent);
                    onPanel = false;
//                        System.out.println("onPanel = " + onPanel);
                }
                //</editor-fold>
            });
            component[i].addKeyListener(new KeyAdapter() {
                //<editor-fold desc="KeyHandler ENTER aby otworzyc DayView">
                @Override
                public void keyReleased(KeyEvent keyEvent) {
//                        System.out.println("keyEvent = " + keyEvent);
                    super.keyReleased(keyEvent);
                    if (keyEvent.getKeyCode() == 10) {
//                            System.out.println("keyEvent = " + keyEvent);
                        DayView window = new DayView(day, month, year);
                        DayView.main(null);
                    }
                    super.keyPressed(keyEvent);

                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == 10) {
//                            System.out.println("keyEvent = " + keyEvent);
                        DayView window = new DayView(day, month, year);
                        DayView.main(null);
                    }
                    super.keyPressed(keyEvent);

                }

                @Override
                public void keyTyped(KeyEvent keyEvent) {

                    //Jezeli Enter otworz DayView
                    if (keyEvent.getKeyCode() == 10) {
//                            System.out.println("keyEvent = " + keyEvent);
                        DayView window = new DayView(day, month, year);
                        DayView.main(null);
                    }
                    super.keyTyped(keyEvent);

                }
                //</editor-fold>
            });
        }


        //<editor-fold desc="When day pressed open DayView">
//        calendar.getDayChooser()
        calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                day = (int) evt.getNewValue();
                month = calendar.getMonthChooser().getMonth() + 1;
                year = calendar.getYearChooser().getYear();
//                System.out.println(pressedEnter);
                if (onPanel) {
                    onPanel = false;
                    DayView window = new DayView(day, month, year);
                    DayView.main(null);
                }

//				System.out.println(calendar.getMonthChooser().getMonth()+1);
//				System.out.println(day);
//				System.out.println(year);
            }
        });
//        calendar.getDayChooser().setEnabled(false);


        //</editor-fold>


    }

    private void checkEvents() {
        ArrayList<Event> events = dataService.getAllEvents();
        for (Event event : events) {
            long dif = event.getStartDate().getTime() - new Date().getTime();
            if (dif < 1800000 && dif > 0 && !event.isAlarm()) {
                event.setAlarm(true);
                try {
                    CloseEvent dialog = new CloseEvent(event);
                    dialog.setTitle("Nadchodzace wydarzenie!");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                    dialog.addConfirmListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dialog.timer.stop();
                            dialog.dispose();
                        }
                    });
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Wystapil blad", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}



