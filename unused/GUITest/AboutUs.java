package GUITest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs {
    private JPanel root;
    private JButton julitaWlodarczykButton;
    private JButton bartlomiejJenczButton;

    public AboutUs() {
    	julitaWlodarczykButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(root, "NR INDEKSU: 216921 \nADRES EMAIL: 216921@edu.p.lodz.pl");
            }
        });
        bartlomiejJenczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(root, "NR INDEKSU: 216783\nADRES EMAIL: 216921@edu.p.lodz.pl");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AboutUs");
        frame.setContentPane(new AboutUs().root);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
