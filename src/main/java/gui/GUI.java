package gui;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame frame;
    private JCalendar calendar;

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

    public GUI() {

        initialize();

    }
    private void initialize() {
        frame = new JFrame("Organizer");
        frame.setBounds(100, 100, 665, 486);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendar = new JCalendar();
        frame.getContentPane().add(calendar);
    }


    }


