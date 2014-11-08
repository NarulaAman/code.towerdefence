package ca.concordia.soen6441.logic;

import java.io.Serializable;

public class EnemyWave implements Serializable{
	
	private final GamePlay gamePlay;
	
	private final float spawnEverySeconds;
	
	private float startDelaySeconds;
	
	private int enemiesToSpawn;
	
	private float lastSpawnDeltaTime;
	
	
	public EnemyWave(GamePlay gamePlay, float spawnEverySeconds, float startDelaySeconds, int enemiesToSpawn) {
		super();
		this.gamePlay = gamePlay;
		this.spawnEverySeconds = spawnEverySeconds;
		this.startDelaySeconds = startDelaySeconds;
		this.enemiesToSpawn = enemiesToSpawn;
	}
	
	
	
	private void spawnEnemy() {
		Enemy e = null;
		gamePlay.addEnemy(e);
	}











	public void update(float seconds) {
		lastSpawnDeltaTime += seconds;
	}
}
