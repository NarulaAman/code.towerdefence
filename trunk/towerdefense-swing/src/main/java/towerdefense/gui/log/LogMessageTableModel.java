package towerdefense.gui.log;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;
import javax.swing.table.AbstractTableModel;

import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LogMessage;

public class LogMessageTableModel extends AbstractTableModel implements Observer{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LogManager logManager;
	@Inject
	public LogMessageTableModel(LogManager logManager) {
		this.logManager = logManager;
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
		return logManager.getLogsFor(null);
	}

	@Override
	public void update(Observable o, Object arg) {
		fireTableDataChanged();
	}
}