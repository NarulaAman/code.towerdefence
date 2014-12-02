package ca.concordia.soen6441.logger.filter;

import java.io.Serializable;

import ca.concordia.soen6441.logger.LogMessage;


/**
 * The Class LogFilter.
 */
public abstract class LogFilter implements Serializable, Comparable<LogFilter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6804957712413471539L;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new log filter.
	 *
	 * @param name the name
	 */
	public LogFilter(String name) {
		super();
		this.name = name;
	}



	/**
	 * This method will return string
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Filter.
	 *
	 * @param logMessage the log message
	 * @return true, if successful
	 */
	public abstract boolean filter(LogMessage logMessage);

	/**
	 * This method will compare  to other
	 * @param other
	 */
	@Override
	public int compareTo(LogFilter o) {
		return name.compareTo(o.name);
	}



	/**
	 * This method will return the hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * This method will check if two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogFilter other = (LogFilter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
