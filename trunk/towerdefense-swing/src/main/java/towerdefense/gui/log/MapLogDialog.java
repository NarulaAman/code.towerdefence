package towerdefense.gui.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JTable;

import towerdefense.gui.guice.GuiModule;
import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logic.GameMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The Class MapLogDialog.
 */
public class MapLogDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JTable table = new JTable();
	
	private final LogMessageTableModel logMessageTableModel;
	private GameMap selectedMap;
	
	/**
	 * Instantiates a new map log dialog.
	 *
	 * @param logMessageTableModel the log message table model
	 */
	@Inject
	public MapLogDialog(LogMessageTableModel logMessageTableModel) {
		
		setMaximumSize(new Dimension(600, 450));
		
		setMinimumSize(new Dimension(600, 450));
		this.logMessageTableModel = logMessageTableModel;

       
		

		// scrollPane.add(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		//logMessageTableModel.setLogMessages(logManager.getLogsFor(new LogFilter(selectedMap.toString())));
		
		add(table, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	/**
	 * Map selected.
	 *
	 * @param selectedMap the selected map
	 */
	public void mapSelected(GameMap selectedMap) {
		this.selectedMap = selectedMap;
	}
	
	private static void createAndShowGUI() throws ClassNotFoundException, IOException {
		
		Injector injector = Guice.createInjector(new GuiModule());
		MapLogDialog mapLogPanel = injector.getInstance(MapLogDialog.class);  
    }
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
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
	
	/**
	 * Sets the map logs.
	 *
	 * @param logMessages the new map logs
	 */
	public void setMapLogs(List<LogMessage> logMessages ) {
		logMessageTableModel.setLogMessages(logMessages);
		table.setModel(logMessageTableModel);
		logMessageTableModel.fireTableDataChanged();
	}
	
	

}
