package GUITest;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JPanel rootPanel;
    private JCalendar JCalendar1;

    public void createUIComponents() {
        JCalendar1 = new JCalendar();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    //    public GUI()
//    {
//        add(rootPanel);
//        setTitle("Title");
//        setSize(400,600);
//    }
}
