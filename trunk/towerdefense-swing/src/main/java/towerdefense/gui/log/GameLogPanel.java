package towerdefense.gui.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ca.concordia.soen6441.logger.LogMessage;


public class GameLogPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTable table;

	private final JScrollPane scrollPane = new JScrollPane();

	private LogMessageTableModel logMessageTableModel;
	private List<LogMessage> logMessages;;

	@Inject
	public GameLogPanel(LogMessageTableModel logMessageTableModel) {

		this.logMessageTableModel = logMessageTableModel;

		table = new JTable();
		table.setModel(logMessageTableModel);

		//scrollPane.add(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		//logMessages = logMessageTable.getLogMessages();



//		for(int i=0; i < table.getModel().getRowCount(); i++) {
//			System.out.println(((LogMessage)table.getModel().getValueAt(i, 0)).getMessage());
//			System.out.println(((LogMessage)table.getModel().getValueAt(i, 0)).getTimeStamp());
//		}
		logMessageTableModel.fireTableDataChanged();
		add(table,BorderLayout.CENTER);
	}



}
