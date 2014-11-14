package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class Enemy extends Observable {
//	private GridPosition titorGridPosition;

//	private char[][] titorMap;
//	private boolean lock;
//	private GridPosition startGridPosition;
//	private GridPosition endGridPosition;
//	private List<Tower> towerList;
//	private List<Tower> towersInMap;

	private/* final */GameMap gameMap;
	private Point2f currentPosition;
	private float speed = 0.5f;
	private int destinationIdx = 1;
	private int prize = 50;
	private int health;

//	public Enemy() {
//		 gameMap= null;
//		health = 0;
//		currentPosition = null;
//	}

	public Enemy(GameMap gameMap, int health, Point2f currentPosition) {
		this.gameMap = gameMap;
		this.health = health;
		this.currentPosition = currentPosition;
	}

//	public char[][] getMap() {
//		return titorMap;
//	}

//	public Enemy(GridPosition p, int health, char[][] map, List<Tower> towersInMap) {
//		titorGridPosition = p;
//		this.health = health;
//		for (int i = 0; i < map.length; i++)
//			for (int j = 0; j < map[0].length; j++) {
//				if (map[i][j] == 'S') {
//					startGridPosition = new GridPosition(i, j);
//					char[][] copy = copyMap(map);
//					titorMap = copy;
//					solveMap(copy, i, j);
//
//				}
//				if (map[i][j] == '@') {
//					endGridPosition = new GridPosition(i, j);
//				}
//
//			}
//		towerList = new ArrayList<Tower>();
//		this.towersInMap = towersInMap;
//		lock = false;
//	}

//	public GridPosition getGridPosition() {
//		return titorGridPosition;
//	}
//
//	public void setPosisiton(int x, int y) {
//		titorGridPosition = new GridPosition(x, y);
//	}

//	public char[][] copyMap(char[][] test1) {
//		char[][] test2 = new char[test1.length][test1[0].length];
//		for (int i = 0; i < test1.length; i++) {
//
//			for (int j = 0; j < test1[0].length; j++) {
//				test2[i][j] = test1[i][j];
//
//			}
//		}
//		return test2;
//	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health != this.health){ 
			this.health = health;
			setChanged();
			notifyObservers();
		}
	}

//	public void lockTitor(Tower t) {
//
//		towerList.add(t);
//		lock = true;
//	}

//	public void unlockTitor(Tower t) {
//		for (Tower i : towerList) {
//			if (i.getGridPosition().getX() == t.getGridPosition().getX()
//					&& i.getGridPosition().getY() == t.getGridPosition().getY()) {
//				towerList.remove(i);
//			}
//		}
//		if (towerList.isEmpty()) {
//			lock = false;
//		}
//
//	}

//	public boolean titorStillAlive() {
//		if (health > 0) {
//			return true;
//		} else
//			return false;
//	}

//	public boolean solveMap(char[][] initialMap, int x, int y) {
//
//		if (x < 0 || x > initialMap.length - 1 || y < 0 || y > initialMap[0].length - 1) {
//			return false;
//		} else if (initialMap[x][y] == '@') {
//			return true;
//		} else if (initialMap[x][y] == '#' || initialMap[x][y] == 'X' || initialMap[x][y] == '!') {
//			return false;
//		} else if (initialMap[x][y] == '-' || initialMap[x][y] == 'S') {
//			initialMap[x][y] = '/';
//			titorMap[x][y] = '/';
//			if (solveMap(initialMap, x + 1, y))
//				return true;
//			if (solveMap(initialMap, x - 1, y))
//				return true;
//			if (solveMap(initialMap, x, y + 1))
//				return true;
//			if (solveMap(initialMap, x, y - 1))
//				return true;
//			initialMap[x][y] = '!';
//			titorMap[x][y] = '!';
//			return false;
//		}
//		return false;
//	}

//	public void newGridPositionVerify() {
//		towerList.clear();
//		lock = false;
//
//		for (Tower i : towersInMap) {
//
//			if (i.canLockTarget(this)) {
//
//				towerList.add(i);
//				lock = true;
//			}
//		}
//		if (lock) {
//			for (Tower i : towerList) {
//				i.shootEnemy(this);
//
//			}
//
//		}
//
//	}
//
//	public boolean move() {
//		if (titorGridPosition.getX() > -1 && titorGridPosition.getY() > -1) {
//
//			if (health > 0) {
//				if (titorGridPosition.getX() + 1 < titorMap.length
//						&& titorMap[titorGridPosition.getX() + 1][titorGridPosition.getY()] == '/') {
//
//					titorMap[titorGridPosition.getX() + 1][titorGridPosition.getY()] = '+';
//					titorGridPosition = new GridPosition(titorGridPosition.getX() + 1, titorGridPosition.getY());
//
//				} else if (titorGridPosition.getX() > 0
//						&& titorMap[titorGridPosition.getX() - 1][titorGridPosition.getY()] == '/') {
//
//					titorMap[titorGridPosition.getX() - 1][titorGridPosition.getY()] = '+';
//					titorGridPosition = new GridPosition(titorGridPosition.getX() - 1, titorGridPosition.getY());
//
//				} else if (titorGridPosition.getY() + 1 < titorMap[0].length
//						&& titorMap[titorGridPosition.getX()][titorGridPosition.getY() + 1] == '/') {
//
//					titorMap[titorGridPosition.getX()][titorGridPosition.getY() + 1] = '+';
//					titorGridPosition = new GridPosition(titorGridPosition.getX(), titorGridPosition.getY() + 1);
//
//				} else if (titorGridPosition.getY() > 0
//						&& titorMap[titorGridPosition.getX()][titorGridPosition.getY() - 1] == '/') {
//
//					titorMap[titorGridPosition.getX()][titorGridPosition.getY() - 1] = '+';
//					titorGridPosition = new GridPosition(titorGridPosition.getX(), titorGridPosition.getY() - 1);
//
//				}
//				newGridPositionVerify();
//				if (titorGridPosition.getX() == endGridPosition.getX()
//						&& titorGridPosition.getY() == endGridPosition.getY()) {
//					return false;
//				} else {
//					return true;
//				}
//			} else {
//				return false;
//			}
//		} else {
//			titorGridPosition = new GridPosition(titorGridPosition.getX() + 1, titorGridPosition.getY() + 1);
//			newGridPositionVerify();
//			return true;
//
//		}
//
//	}

	/**
	 * Updates the state of the enemy by a given amount of time has passed
	 * @param seconds seconds passed
	 */
	public void update(float seconds) {
		if(!hasReachedEnd()) {
			System.out.println("Enemy at " + currentPosition + " nextIdx " + destinationIdx + " on position " + getNextPosition());
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
	 * Returns the direction vector from the current position to the given position
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
//		if (currentPosition == null) {
//			return new Point2f(titorGridPosition.getX(), titorGridPosition.getY());
//		}
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
	
}
