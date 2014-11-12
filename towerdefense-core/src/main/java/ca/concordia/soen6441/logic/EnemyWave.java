package ca.concordia.soen6441.logic;

import java.io.Serializable;

import javax.vecmath.Point2f;

public class EnemyWave implements Serializable{
	
	private final GamePlay gamePlay;
	
	private final GameMap gameMap;
	
	private final float spawnEverySeconds;
	
//	private float startDelaySeconds;
	
	private int enemiesToSpawn;
	
	private float lastSpawnDeltaTime;
	
	public EnemyWave(GamePlay gamePlay, float spawnEverySeconds, /*float startDelaySeconds, */ int enemiesToSpawn) {
		super();
		this.gamePlay = gamePlay;
		this.spawnEverySeconds = spawnEverySeconds;
//		this.startDelaySeconds = startDelaySeconds;
		this.enemiesToSpawn = enemiesToSpawn;
		gameMap = gamePlay.getMap();
	}	
	
	
	
	public void spawnEnemy() {
		Enemy e = new Enemy(gameMap, 100, new Point2f(gameMap.getStartGridPosition().getX(), gameMap.getStartGridPosition().getY()));
		gamePlay.addEnemy(e);
		enemiesToSpawn = enemiesToSpawn - 1;
	}
	
	public void update(float seconds) {
		lastSpawnDeltaTime = lastSpawnDeltaTime + seconds;
		while (lastSpawnDeltaTime > spawnEverySeconds) {
			lastSpawnDeltaTime = lastSpawnDeltaTime - spawnEverySeconds;
			spawnEnemy();
		}
	}
	
	public boolean isFinished() {
		return enemiesToSpawn < 1;
	}
}
