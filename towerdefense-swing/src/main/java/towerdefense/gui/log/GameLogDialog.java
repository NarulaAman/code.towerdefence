package towerdefense.gui.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import towerdefense.gui.guice.GuiModule;
import ca.concordia.soen6441.logger.LogFilter;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LogMessage;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameLogDialog extends JDialog implements Observer {

	private final JTable table = new JTable();

	private final JScrollPane scrollPane = new JScrollPane();
	
	private final JComboBox<String> comboBox = new JComboBox<String>();
	private final LogMessageTableModel logMessageTableModel;
	
	private final LogManager logManager;
	
	private Object displayed = null;
	
	@Inject
	public GameLogDialog(LogMessageTableModel logMessageTableModel, LogManager logManager) {
		this.logMessageTableModel = logMessageTableModel;
		this.logManager = logManager;
       
		table.setModel(logMessageTableModel);

		// scrollPane.add(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		logMessageTableModel.setLogMessages(logManager.getLogsFor(new LogFilter("sdf")));

		logMessageTableModel.fireTableDataChanged();
		add(table, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	
	public void displayLogs(Object object) {
		if (object != displayed) {
			displayed = object;
			showLogsFor(object);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			logMessageTableModel.setLogMessages(new ArrayList<LogMessage>());
		} else if (arg == displayed) {
			showLogsFor(arg);
		}
	}


	private void showLogsFor(Object arg) {
		logMessageTableModel.setLogMessages(logManager.getLogsFor(new LogFilter("sdf")));
	}
	
//	/**
	//	 * Creates the GUI for testing purposes
	//	 * @throws ClassNotFoundException
	//	 * @throws IOException
	//	 */
		private static void createAndShowGUI() throws ClassNotFoundException, IOException {
			//Create and set up the window.
//			JFrame frame = new JFrame("GameLogPanel");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Injector injector = Guice.createInjector(new GuiModule());
			GameLogDialog gameLogPanel = injector.getInstance(GameLogDialog.class);  
			
////			GameLogPanel gameLogPanel = new GameLogPanel();
//			frame.setContentPane(gameLogPanel);
	
			//Display the window.
//			frame.pack();
//			frame.setVisible(true);
	    }
	//
	//	/**
	//	 * Main method used for testing the GUI
	//	 * @param args arguments are ignored by this method
	//	 */
		public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						createAndShowGUI();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

	
}
