package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import ca.concordia.soen6441.logger.Log;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.Tower;

import com.google.inject.assistedinject.Assisted;


/**
 * This class has logic for various operations executed on Game Play
 *
 */
public class GamePlay extends Observable implements Serializable, Observer {

	private static final long serialVersionUID = 1L;

	private final LogManager logManager = new LogManager();
	
	private final GameMap gameMap;
	
	private String name = "gamePlay1";
	
	private final List<EnemyWave> enemyWaves = new CopyOnWriteArrayList<>();
	private EnemyWave currentWave;
	
	private final TowerFactory towerFactory = new TowerFactory();
	
	private final List<Enemy> enemies = new CopyOnWriteArrayList<>();
	
	private final List<Tower> towers = new ArrayList<>();
	
	private int waveId = 0;
	
	private int enemyId = 0;
	
	private int score = 0;
	
	private int level = 1;
	
	private int currency;
	
	private int lives=10;
	
	/**
	 * Internal state of the {@link GamePlay}
	 *
	 */
	private enum State {
		/**
		 * Setup state
		 */
		SETUP,
		/**
		 * Running state
		 */
		RUNNING,
		/**
		 * GameOver state
		 */
		GAMEOVER
	}
	
	private State gameState;
	
	
	

	/**
	 * Build a GamePlay instance
	 * @param gameMap The map selected for Play
	 * @param currency The amount available
	 */
	@Inject
	public GamePlay(@Assisted GameMap gameMap, @Assisted int currency) {
		super();
		this.gameMap = gameMap;
		this.currency = currency;
		this.lives = 10;
		//this.name ="";
		
		
		// TODO: remove the lines below, it is only for testing
		
//		for (int x = 0; x < gameMap.getWidth(); ++x) {
//			for (int y = 0; y < gameMap.getHeight(); ++y) {
//				if (x != y) continue;
//				enemyPath.add(new GridPosition(x, y));
//			}
//		}
//		addEnemy(new Enemy(this, 100, new Point2f(gameMap.getStartGridPosition().getX(), gameMap.getStartGridPosition().getY())));
		
		// TODO: end of lines to be removed
		setState(State.SETUP);
		createNextWave();
		
	}
	
	/**
	 * Starts the {@link GamePlay}
	 */
	public void start() {
		setState(State.RUNNING);
	}

	/**
	 * Validate if the user can buy and place the {@link Tower} or not
	 * @param tower The object of class {@link Tower}
	 * @return True if the user can buy tower else false
	 */
	public boolean tryToBuy(Tower tower) {
		if (canPlace(tower) && hasEnoughMoneyToBuy(tower)) {
			currency = currency - tower.getBuyCost();
			buy(tower);
			notifyWithChange();
			return true;
		} else {
			return false;
		}
	}

	
	private void createNextWave() {
		currentWave = new EnemyWave(this, getNextWaveId(), 5.f/level, 5 * level);
		logManager.log(this, "%1%s set-up", currentWave);
	}
	
	/**
	 * Effectively buys the tower
	 * @param tower tower that was bought
	 */
	@Log("Bought %2$s")
	protected void buy(Tower tower) {
		towers.add(tower);
	}

	/**
	 * Check if there is enough money to buy tower
	 * @param tower tower to be bough
	 * @return true if there is enough money, false if not
	 */
	private boolean hasEnoughMoneyToBuy(Tower tower) {
		return tower.getBuyCost() <= currency;
	}
	
	/**
	 * Validate the correct position of tower at {@link GameMap}
	 * @param tower The position of tower on {@link GameMap}
	 * @return True or False if the position of the Tower is correct or incorrect
	 */
	public boolean canPlace(Tower tower) {
		if (getMap().hasStartTile()) {
			if (tower.getGridPosition().equals(getMap().getStartGridPosition())) {
				return false;
			}
		}
		if (getMap().hasEndTile()) {
			if (tower.getGridPosition().equals(getMap().getEndGridPosition())) {
				return false;
			}
		}
		if(hasTower(tower.getGridPosition())){
			return false;
		}
		Tile tile = getMap().getTile(tower.getGridPosition());
		return tile == Tile.SCENERY;
	}

	
	/**
	 * Validate if the tower is present at give position
	 * @param gridPosition The X and Y coordinate of {@link Tile} on {@link GameMap}
	 * @return True if the Tower is present at give position
	 */
	public boolean hasTower(GridPosition gridPosition) {
		for (Tower tower : towers) {
			if (tower.getGridPosition().equals(gridPosition)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the object of {@link Tower} at give position. hasTower should be called first to check if a tower
	 * exists in the {@link GridPosition}
	 * @param gridPosition The X and Y coordinate of {@link Tile} on {@link GameMap}
	 * @return The object of {@link Tower}
	 */
	public Tower getTower(GridPosition gridPosition) {
		for (Tower tower : towers) {
			if (tower.getGridPosition().equals(gridPosition)) {
				return tower;
			}
		}
		return null; // we should never get here if the proper use of the API was made, i.e. hasTower was called first
	}

	/**
	 * Tries to upgrade the tower. Returns true if the upgrade happened, false
	 * if not
	 * 
	 * @param tower To be upgraded
	 * @return true if the upgrade happened, false if not
	 */
	public boolean tryToUpgrade(Tower tower) {
		// implement upgrade logic here
		if (tower.canUpgrade() && currency >= tower.getUpgradeCost()) {
			currency = currency - tower.getUpgradeCost();
			upgrade(tower);
			notifyWithChange();
			return true;
		} else {
			return false;
		}
	}

	@Log("Upgraded %2$s")
	protected void upgrade(Tower tower) {
		tower.doUpgrade();
	}
	/**
	 * Notify the Observers that there was a change
	 */
	private void notifyWithChange() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Remove the tower from {@link GameMap} and refund the currency amount
	 * @param tower To be refunded
	 */
	public void sell(Tower tower) {
		currency = currency + tower.getRefundRate();
		towers.remove(tower);
		notifyWithChange();
	}
	

	/**
	 * Returns the total number of towers on the {@link GameMap}
	 * @return The total number of towers on the {@link GameMap}
	 */
	public int totalTowers() {
		return towers.size();
	}
	
	/**
	 * Returns amount of currency
	 * @return currency
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * Returns the current {@link GameMap}
	 * @return the current {@link GameMap}
	 */
	public GameMap getMap() {
		return gameMap;
	}

	/**
	 * Returns a copy of the list of Towers 
	 * @return a copy of the list of Towers
	 */
	public List<Tower> getTowers() {
		return new ArrayList<>(towers);
	}

	/**
	 * Returns the number of lives
	 * @return the number of lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Update the simulation passing how many seconds have elapsed since the last call
	 * @param seconds delta seconds passed since the last call
	 */
	public void update(float seconds) {
		if (gameState != State.RUNNING) {
			return;
		}
		
		if (currentWave != null) {
//			EnemyWave enemyWave = enemyWaves.get(0);
			currentWave.update(seconds);

		}
				
		for (Enemy enemy : enemies) {
			enemy.update(seconds);
			if (enemy.hasReachedEnd()) {
				reachedEnd(enemy);
			}
			
		}
		
		for (Tower tower : towers) {
			tower.update(seconds);
			tower.maybeShoot(enemies);
		}
		detectGameOver();
		updateWaveFinished();
		setChanged();
		notifyObservers();
	}

	
	/**
	 * Update the internal state if a wave has finished
	 */
	private void updateWaveFinished() {
		if (enemies.isEmpty() && currentWave.isFinished()) {
			setState(State.SETUP);		
			createNextWave();
		}
	}
	

	/**
	 * Add enemy wave to the game play
	 * @param enemyWave the new enemy wave added to the game play
	 */
	private void addEnemyWave(EnemyWave enemyWave) {
		enemyWaves.add(enemyWave);
	}
	
	/**
	 * Adds an enemy to the {@link GamePlay}
	 * @param enemy enemy to be added to the {@link GamePlay}
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		enemy.addObserver(this);
	}
	
	/**
	 * Notifies the {@link GamePlay} that the enemy has reached the end
	 * @param enemy {@link Enemy} that has reached the end
	 */
	public void reachedEnd(Enemy enemy) {
		enemies.remove(enemy);
		currency = currency - enemy.getPrize();
		lives--;
		setChanged();
		notifyObservers();
	}
	/**
	 * Test if the game is over 
	 * @return true if game is over and false if not
	 */
	private boolean detectGameOver() {
		if(currency<=0 || lives<=0) {
			setState(State.GAMEOVER);
			return isStateGameOver();
		}
		return false;
	}

	/**
	 * Returns the enemies alive in the {@link GamePlay}
	 * @return the enemies alive in the {@link GamePlay}
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Update this observer
	 * @param o observable
	 * @param arg argument
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Enemy) {
			Enemy enemy = (Enemy) o;
			if (!enemy.isAlive()) {
				enemies.remove(enemy);
				currency = currency + enemy.getPrize();
				setChanged();
				notifyObservers();
			}
		}
		
	}
	/**
	 * Set states for the game to new state 
	 * @param newState value of the new state of the game
	 */
	private void setState(State newState) {
		if (newState != gameState) {
			gameState = newState;
			setChanged();
			notifyObservers();
		}
	}


	/**
	 * Returns if the {@link GamePlay} is in setup state
	 * @return if the {@link GamePlay} is in setup state
	 */
	public boolean isStateSetup() {
		return gameState == State.SETUP;
	}
	
	/**
	 * Returns if the {@link GamePlay} is in Game Over state
	 * @return if the {@link GamePlay} is in Game Over state
	 */
	public boolean isStateGameOver() {
		return gameState == State.GAMEOVER;
	}
	/**
	 * Returns if the {@link GamePlay} is in running state
	 * @return if the {@link GamePlay} is in running state
	 */
	public boolean isStateRunning() {
		return gameState == State.RUNNING;
	}
	
	/**
	 * Returns the name of the Game Play
	 * 
	 * @return the name of the Game Play
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the Game Play
	 * @param name is the name of the Game Play
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public int getNextEnemyId() {
		return ++enemyId;
	}
	
	private int getNextWaveId() {
		return ++waveId;
	}

	public TowerFactory getTowerFactory() {
		return towerFactory;
	}
	
	
}
