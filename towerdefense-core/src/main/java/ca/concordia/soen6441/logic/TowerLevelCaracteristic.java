package ca.concordia.soen6441.logic;

public class TowerLevelCaracteristic {

	final int damage;
	
	final int buyCost;
	
	/**
	 * Percentage of refund rate
	 */
	final int refundRate;
	
	final int range;

	public TowerLevelCaracteristic(int damage, int buyCost, int refundRate, int range) {
		super();
		this.damage = damage;
		this.buyCost = buyCost;
		this.refundRate = refundRate;
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public int getRefundRate() {
		return refundRate;
	}

	public int getRange() {
		return range;
	}

	public String toHtmlString() {
		return "TowerLevelCaracteristic [damage=" + damage + ", buyCost="
				+ buyCost + ", refundRate=" + refundRate + ", range=" + range
				+ "]";
	}
	
	
	
	
	
	
	
}
