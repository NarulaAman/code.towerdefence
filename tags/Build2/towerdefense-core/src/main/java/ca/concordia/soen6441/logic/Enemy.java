package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import ca.concordia.soen6441.logic.primitives.GridPosition;
/**
 * 
 * This class has various characteristics of Enemy
 *
 */
public class Enemy extends Observable {

	private final GameMap gameMap;
	private final int maxHealth;
	private Point2f currentPosition;
	private float speed = 1.5f;
	private int destinationIdx = 1;
	private int prize = 50;
	private int health;

	/**
	 * Initialize the data members
	 * @param gameMap The object {@link GameMap}
	 * @param health The health of {@link Enemy}
	 * @param currentPosition The position of {@link Enemy}
	 */
	public Enemy(GameMap gameMap, int health, Point2f currentPosition) {
		this.gameMap = gameMap;
		this.health = health;
		this.currentPosition = currentPosition;
		this.maxHealth = health;
	}


	/**
	 * Returns the health of {@link Enemy}
	 * @return The health of {@link Enemy}
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Set the health of {@link Enemy}
	 * @param health Health of the {@link Enemy}
	 */
	public void setHealth(int health) {
		if (health != this.health){ 
			this.health = health;
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Updates the state of the enemy by a given amount of time has passed
	 * @param seconds seconds passed
	 */
	public void update(float seconds) {
		if(!hasReachedEnd()) {
			//System.out.println("Enemy at " + currentPosition + " nextIdx " + destinationIdx + " on position " + getNextPosition());
			move(seconds);
		}
	}

	/**
	 * Returns true of the enemy has reached the end, false if not
	 * @return true of the enemy has reached the end, false if not
	 */
	public boolean hasReachedEnd() {
		return destinationIdx >= gameMap.getStartToEndPath().size();
	}

	/**
	 * Moves this enemy by the amount of seconds passed
	 * @param seconds amount of seconds passed
	 */
	private void move(float seconds) {
		Point2f nextPosition = getNextPosition();

		float timeForNextTile = currentPosition.distance(nextPosition) / speed;
		// if the enemy doesn't reach the next tile
		if (seconds < timeForNextTile) {
			float walkedDistance = speed * seconds;
			Vector2f direction = directionTo(nextPosition);	
			direction.scale(walkedDistance);
			currentPosition.add(direction);
		}
		// else he passed the next tile and now goes to the following, if it exists
		else {
			currentPosition = nextPosition;
			destinationIdx++;
			update(seconds - timeForNextTile);
		}
	}

	/**
	 * Returns the direction vector from the current position to given position
	 * @param otherPosition other given position to have the vector directed to
	 * @return the direction vector from the current position to the given position
	 */
	private Vector2f directionTo(Point2f otherPosition) {
		Vector2f directionToMove = new Vector2f(otherPosition);
		directionToMove.sub(currentPosition);
		directionToMove.normalize();
		return directionToMove;
	}

	/**
	 * Returns the next position the {@link Enemy} should walk to 
	 * @return the next position the {@link Enemy} should walk to 
	 */
	public Point2f getNextPosition() {
		GridPosition position = gameMap.getStartToEndPath().get(destinationIdx);
		return new Point2f(position.getX(), position.getY());
	}

	/**
	 * Returns the current position of the enemy on the map
	 * @return the current position of the enemy on the map
	 */
	public Point2f getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Returns true if the enemy is alive, false if not
	 * @return true if the enemy is alive, false if not
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Returns the speed, which is units of the grid per second. Can also be understood as tiles per second.
	 * @return the speed, which is units of the grid per second.
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the enemy in units per second. Can also be understood as tiles per second.
	 * @param speed the speed, which is units of the grid per second.
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Makes the enemy take an amount of Damage
	 * @param damage damage to be taken by the enemy
	 */
	public void takeDamage(int damage) {
		setHealth(getHealth() - damage); 
	}
	
	/**
	 * Returns the amount of money gained by the player when the enemy is killed 
	 * @return the amount of money gained by the player when the enemy is killed 
	 */
	public int getPrize() {
		return prize;
	}
	
	/**
	 * Returns the progress of the {@link Enemy} in the {@link GameMap}
	 * @return the progress of the {@link Enemy} in the {@link GameMap}
	 */
	public float getProgress() {
		float totalDistance = 0;
		GridPosition prev = gameMap.getStartToEndPath().get(0);
		for (int i = 1; i < gameMap.getStartToEndPath().size(); ++i) {
			GridPosition current = gameMap.getStartToEndPath().get(i);
			totalDistance = totalDistance + prev.distance(current);
			prev = current;
		}
		
		float currentDistance = currentPosition.distance(getNextPosition());
		prev = gameMap.getStartToEndPath().get(destinationIdx);
		for (int i = destinationIdx + 1; i < gameMap.getStartToEndPath().size(); ++i) {
			GridPosition current = gameMap.getStartToEndPath().get(i);
			currentDistance = currentDistance + prev.distance(current);
			prev = current;
		}
		return 1 - currentDistance / totalDistance;
	}
	
	/**
	 * Returns the max health
	 * @return the max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
}
