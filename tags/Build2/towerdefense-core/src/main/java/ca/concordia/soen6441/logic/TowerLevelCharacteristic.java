package ca.concordia.soen6441.logic;

/**
 * This class contain the characteristics of Tower as per level, 
 * it is an immutable class to simplify the implementation
 *
 */
public class TowerLevelCharacteristic {

	private final int damage;
	
	private final int buyCost;

	private final int refund;
	
	private final int range;
	
	private final float shootRateSecs;

	/**
	 * Construct the tower characteristics
	 * @param damage caused by the tower
	 * @param buyCost of the tower
	 * @param refund of the tower
	 * @param range of the tower
	 * @param shootRateSecs time in seconds between each shot
	 */
	public TowerLevelCharacteristic(int damage, int buyCost, int refund, int range, float shootRateSecs) {
		super();
		this.damage = damage;
		this.buyCost = buyCost;
		this.refund = refund;
		this.range = range;
		this.shootRateSecs = shootRateSecs;
	}

	/**
	 * Returns the damage caused by this tower
	 * @return the damage caused by this tower
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Returns the buy cost of this tower
	 * @return the buy cost of this tower
	 */
	public int getBuyCost() {
		return buyCost;
	}

	/**
	 * Returns the refund cost of this tower
	 * @return the refund cost of this tower
	 */
	public int getRefund() {
		return refund;
	}

	/**
	 * Returns the range cost of this tower
	 * @return the range cost of this tower
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Returns the time in seconds for every shot of the tower
	 * @return the time in seconds for every shot of the tower
	 */
	public float getShootRateSecs() {
		return shootRateSecs;
	}
	
	/** 
	 * Returns a html string with the damage, buyCost, refund and range of tower
	 * @return a html string with the damage, buyCost, refund and range of tower
	 */
	public String toHtmlString() {
		return "<html>Damage: " + damage + "<br>Buy cost: "
				+ buyCost + "<br>Refund: " + refund + "<br>Range: " + range
				+ "</html>";
	}
	
	
	
	
	
	
	
}
