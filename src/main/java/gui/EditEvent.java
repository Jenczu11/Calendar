package gui;

import service.DataService;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;

public class EditEvent extends Events {
	
	private DayView dayView;
    private DataService dService;

	/*
	 * Launch the application.
     * Nalezy odkomentowac konstruktor aby moc uruchomic okienko bezposrednio
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	EditEvent window = new EditEvent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public EditEvent(Timestamp date, DefaultTableModel model, DayView dw) {
		super(date, model, dw);
		// TODO Auto-generated constructor stub
	}
	  private EditEvent() {
		  super();
	        dService = DataService.getInstance();
	        Events.initialize();
	        Events.button.setLabel("Edytuj");
	        Events.label.setText("Edytuj wydarzenie");
	    }

}
