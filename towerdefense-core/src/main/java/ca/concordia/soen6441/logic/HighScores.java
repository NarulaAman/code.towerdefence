package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * This class will maintain the high scores of the map
 * 
 */
public class HighScores implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 7943086470924125701L;

	private String name = "HighScores";	
	List<Integer> highScore= new  ArrayList<>();
	
/**
 * This method will add and order the high score	
 * @param score The score to be added to the list
 */
	public void addHighScore(Integer score)
	{
		
		highScore.add(score);		
		Comparator<Integer> comparator = Collections.reverseOrder();
		highScore.sort(comparator);
		if(highScore.size()>5)
		{
			highScore.remove(5);
		}
		
	}
	
	/**
	 * Returns the name of the Map
	 * @return  the name of the Map
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the map
	 * @param name name to be given for the map
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns true if this object is the same as the other, false if not
	 * @return true if this object is the same as the other, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighScores other = (HighScores) obj;
		if (highScore.size() != other.highScore.size())
			return false;
		
		if (!highScore.equals(other.highScore))
			return false;
		return true;
	}


}
