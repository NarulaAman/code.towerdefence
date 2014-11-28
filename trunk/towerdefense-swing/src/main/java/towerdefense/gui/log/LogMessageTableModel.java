package towerdefense.gui.log;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.concordia.soen6441.logger.LogMessage;

public class LogMessageTableModel extends AbstractTableModel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LogMessage> logMessages = new ArrayList<>();
	
	@Override
	public int getRowCount() {
		return getLogMessages().size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LogMessage logMessage = getLogMessages().get(rowIndex);
		if (columnIndex == 0) {
			return logMessage.getTimeStamp();
		}
		else if (columnIndex == 1) {
			return logMessage.getMessage();
		}
		else {
			return "NOT IMPLEMENTED";
		}
	}
	
	public List<LogMessage> getLogMessages() {
		return logMessages;
	}

	public void setLogMessages(List<LogMessage> logMessages) {
		this.logMessages = logMessages;
		fireTableDataChanged();
		
	}
}