package ca.concordia.soen6441.logic.tower;


/**
 * Visitor Pattern to be applied to the {@link Tower} subclasses.
 */
public interface TowerVisitor {

	/**
	 * Visits a {@link FireTower}.
	 *
	 * @param tower to be visited
	 */
	public void visit(FireTower tower);
	
	/**
	 * Visits a {@link IceTower}.
	 *
	 * @param tower to be visited
	 */
	public void visit(IceTower tower);
	
	/**
	 * Visits a {@link CannonTower}.
	 *
	 * @param tower to be visited
	 */
	public void visit(CannonTower tower);
}
