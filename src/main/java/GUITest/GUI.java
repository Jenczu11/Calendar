package GUITest;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel rootPanel;
    private JCalendar JCalendar1;
    private JButton aboutUSButton;

    public GUI() {
        aboutUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.main(null);
            }
        });
    }

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
