package ca.concordia.soen6441.logic.tower;

public interface TowerVisitor {

	public void visit(FireTower tower);
	public void visit(IceTower tower);
	public void visit(CannonTower tower);
}
