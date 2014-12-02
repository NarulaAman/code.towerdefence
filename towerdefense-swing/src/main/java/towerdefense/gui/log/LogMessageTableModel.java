package towerdefense.gui.log;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ca.concordia.soen6441.logger.LogMessage;

/**
 * The Class LogMessageTableModel.
 */
public class LogMessageTableModel extends AbstractTableModel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LogMessage> logMessages = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return getLogMessages().size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
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
	
	 /* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	 public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Time";
		}
		else if (columnIndex == 1) {
			return "Log message";
		}
		else {
			return "NOT IMPLEMENTED";
		}
	 }
	
	/**
	 * Gets the log messages.
	 *
	 * @return the log messages
	 */
	public List<LogMessage> getLogMessages() {
		return logMessages;
	}

	/**
	 * Sets the log messages.
	 *
	 * @param logMessages the new log messages
	 */
	public void setLogMessages(List<LogMessage> logMessages) {
		this.logMessages = logMessages;
		fireTableDataChanged();
		
	}
}