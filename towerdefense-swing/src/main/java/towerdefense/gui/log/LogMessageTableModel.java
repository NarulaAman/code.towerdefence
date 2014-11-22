package towerdefense.gui.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.concordia.soen6441.logger.LogMessage;

public class LogMessageTableModel extends AbstractTableModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LogMessage> logMessages = new ArrayList<>();

	public LogMessageTableModel() {
		logMessages.add(new LogMessage(new Date(), "amand"));
		logMessages.add(new LogMessage(new Date(), "alex"));
		logMessages.add(new LogMessage(new Date(), "amado"));
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return getLogMessages().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void showLogsFor(Object object) {
		
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
}