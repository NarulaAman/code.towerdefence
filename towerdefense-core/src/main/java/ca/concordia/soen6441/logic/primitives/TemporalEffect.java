package ca.concordia.soen6441.logic.primitives;

import ca.concordia.soen6441.logic.Enemy;

/**
 * This class will manage the temporary affect of {@link Tower} on {@link Enemy}
 *
 */
public class TemporalEffect {
	
	boolean active = false;
	float duration;
	float timeLeft;
	/**
	 * Initialize the data members
	 */
	public TemporalEffect(float duration) {
		this.duration = duration;
		timeLeft = duration;
	}
	
	/**
	 * Enable the affect on {@link Enemy}
	 */
	public void start() {
		active = true;
		startEffect();
	}
	/**
	 * Stop the affect if the specific time has passed
	 * @param seconds The number of seconds passed
	 */
	public void update(float seconds) {
		timeLeft = timeLeft - seconds;
		if (isActive())
		{
			updateEffect(seconds);
			if (isTimedOut()) {
				active = false;
				stopEffect();
			}
		}
	}
	/**
	 * Return True if the affect is active
	 * @return True if the affect is active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * Return True if the time is out   
	 * @return Return true if the time is out
	 */
	public boolean isTimedOut() {
		return timeLeft <= 0; 
	}
	
	protected void startEffect() {};
	
	protected void updateEffect(float seconds) {};
	
	protected void stopEffect() {};
	
	public void resetEffect() {};

}
