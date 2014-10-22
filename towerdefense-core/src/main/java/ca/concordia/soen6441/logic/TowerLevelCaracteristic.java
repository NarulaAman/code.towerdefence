package ca.concordia.soen6441.logic;

/**
 * This class contain the characteristics of Tower as per level
 *
 */
public class TowerLevelCaracteristic {

	final int damage;
	
	final int buyCost;
	
	/**
	 * Percentage of refund rate
	 */
	final int refundRate;
	
	final int range;

	/**
	 * Construct the tower characteristics
	 * @param damage caused by the tower
	 * @param buyCost of the tower
	 * @param refundRate of the tower
	 * @param range of the tower
	 */
	public TowerLevelCaracteristic(int damage, int buyCost, int refundRate, int range) {
		super();
		this.damage = damage;
		this.buyCost = buyCost;
		this.refundRate = refundRate;
		this.range = range;
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
	 * Returns the refundRate cost of this tower
	 * @return the refundRate cost of this tower
	 */
	public int getRefundRate() {
		return refundRate;
	}

	/**
	 * Returns the range cost of this tower
	 * @return the range cost of this tower
	 */
	public int getRange() {
		return range;
	}

	/** Returns the damage, buyCost, refundRate and range of tower
	 * @return the damage, buyCost, refundRate and range of tower
	 */
	public String toHtmlString() {
		return "<html>Damage: " + damage + "<br>Buy cost: "
				+ buyCost + "<br>Refund: " + refundRate + "<br>Range: " + range
				+ "</html>";
	}
	
	
	
	
	
	
	
}
