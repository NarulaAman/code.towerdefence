package ca.concordia.soen6441.logger;

import java.io.Serializable;

public class LogFilter implements Serializable, Comparable<LogFilter>{

	private String name;
	
	public LogFilter(String name) {
		super();
		this.name = name;
	}



	@Override
	public String toString() {
		return name;
	}
	
	boolean filter(LogMessage logMessage) {
		return true;
	}

	@Override
	public int compareTo(LogFilter o) {
		return name.compareTo(o.name);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



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
