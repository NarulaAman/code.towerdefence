package ca.concordia.soen6441.logger;

import java.io.Serializable;

public class LogFilter implements Serializable{

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
}
