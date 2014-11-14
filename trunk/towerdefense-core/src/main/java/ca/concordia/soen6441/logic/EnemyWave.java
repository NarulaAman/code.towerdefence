package ca.concordia.soen6441.logic;

import java.io.Serializable;

import javax.vecmath.Point2f;

/**
 * 
 * This class spawn {@link Enemy} into the {@link GamePlay}
 */
public class EnemyWave implements Serializable{
	
	private final GamePlay gamePlay;
	
	private final GameMap gameMap;
	
	private final float spawnEverySeconds;
	
//	private float startDelaySeconds;
	
	private int enemiesToSpawn;
	
	private float lastSpawnDeltaTime;
	/**
	 * Initialize the data members
	 * @param gamePlay The object of {@link GamePlay}
	 * @param spawnEverySeconds After number of seconds the {@link Enemy} should be spawn in {@link GamePlay}
	 * @param enemiesToSpawn Number of {@link Enemy} to be spawn
	 */
	public EnemyWave(GamePlay gamePlay, float spawnEverySeconds, /*float startDelaySeconds, */ int enemiesToSpawn) {
		super();
		this.gamePlay = gamePlay;
		this.spawnEverySeconds = spawnEverySeconds;
//		this.startDelaySeconds = startDelaySeconds;
		this.enemiesToSpawn = enemiesToSpawn;
		gameMap = gamePlay.getMap();
	}	
	
	
	/**
	 * Spawn the {@link Enemy} into the {@link GamePlay}
	 */
	public void spawnEnemy() {
		Enemy e = new Enemy(gameMap, 100, new Point2f(gameMap.getStartGridPosition().getX(), gameMap.getStartGridPosition().getY()));
		gamePlay.addEnemy(e);
		enemiesToSpawn = enemiesToSpawn - 1;
	}
	
	/**
	 * Spawn the {@link Enemy} into the {@link GamePlay} after interval of time 
	 * @param seconds The number of seconds
	 */
	public void update(float seconds) {
		lastSpawnDeltaTime = lastSpawnDeltaTime + seconds;
		while (lastSpawnDeltaTime > spawnEverySeconds) {
			lastSpawnDeltaTime = lastSpawnDeltaTime - spawnEverySeconds;
			spawnEnemy();
		}
	}
/*
 * Check if all the enemies are spawned
 */
	public boolean isFinished() {
		return enemiesToSpawn < 1;
	}
}
