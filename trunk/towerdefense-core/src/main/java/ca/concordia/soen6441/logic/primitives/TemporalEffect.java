package ca.concordia.soen6441.logic.primitives;

public class TemporalEffect {
	
	boolean active = false;
	float duration;
	float timeLeft;
	
	public TemporalEffect(float duration) {
		this.duration = duration;
		timeLeft = duration;
	}
	
	public void start() {
		active = true;
		startEffect();
	}
	
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
	public boolean isActive() {
		return active;
	}
	
	public boolean isTimedOut() {
		return timeLeft <= 0; 
	}
	
	protected void startEffect() {};
	
	protected void updateEffect(float seconds) {};
	
	protected void stopEffect() {};
	
	public void resetEffect() {};

}
