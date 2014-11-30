package towerdefense.gui.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JTable;

import towerdefense.gui.guice.GuiModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ca.concordia.soen6441.logger.LogFilter;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logic.GameMap;

public class MapLogDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JTable table = new JTable();
	
	private final LogMessageTableModel logMessageTableModel;
	private GameMap selectedMap;
	
	@Inject
	public MapLogDialog(LogMessageTableModel logMessageTableModel) {
		
		setMaximumSize(new Dimension(600, 450));
		
		setMinimumSize(new Dimension(600, 450));
		this.logMessageTableModel = logMessageTableModel;

       
		table.setModel(logMessageTableModel);

		// scrollPane.add(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		//logMessageTableModel.setLogMessages(logManager.getLogsFor(new LogFilter(selectedMap.toString())));
		logMessageTableModel.fireTableDataChanged();
		add(table, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	public void mapSelected(GameMap selectedMap) {
		this.selectedMap = selectedMap;
	}
	
	private static void createAndShowGUI() throws ClassNotFoundException, IOException {
		
		Injector injector = Guice.createInjector(new GuiModule());
		MapLogDialog mapLogPanel = injector.getInstance(MapLogDialog.class);  
    }
	
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
	
	public void setMapLogs(List<LogMessage> logMessages ) {
		logMessageTableModel.setLogMessages(logMessages);
	}
	
	

}
