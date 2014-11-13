package towerdefense.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.concordia.soen6441.logic.tower.Tower;

/**
 * This is the TowerInspectionWindow, it shows the current tower attributes
 */
public class TowerPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846743688938454973L;
	private final JLabel costLbl = new JLabel("Buying cost:");
	private final JLabel damageLbl = new JLabel("Damage:");
	private final JLabel rangeLbl = new JLabel("Range:");
	private final JLabel levelLbl = new JLabel("Level:");
	private final JLabel fireRateLbl = new JLabel("Fire Rate:");
	private final JLabel refundLbl = new JLabel("Refund:");
	private final JLabel upgradeCostLbl = new JLabel("Upgrade cost:");

	private final JTextField costTxtFld = new JTextField("");
	private final JTextField damageTxtFld = new JTextField("");
	private final JTextField rangeTxtFld = new JTextField("");
	private final JTextField levelTxtFld = new JTextField("");
	private final JTextField fireRateTxtFld = new JTextField("");
	private final JTextField refundTxtFld = new JTextField("");
	private final JTextField upgradeCostTxtFld = new JTextField("");

	private final JButton upgradeBtn = new JButton("Upgrade");
	private final JButton sellBtn = new JButton("Sell");
	private final JButton weakestStratBtn = new JButton("Weakest Strategy");
	private final JButton closestStratBtn = new JButton("Closest Strategy");
	private final JButton shootingStratBtn = new JButton("Shooting Strategy");

	private Tower shownTower = null;


	/**
	 * Constructs a {@link TowerPanel}
	 */
	public TowerPanel()
	{
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		setMinimumSize(new Dimension(200, 350));
		setMaximumSize(new Dimension(200, 350));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = constraints.weighty = 1.0;

		JPanel attributesPnl = new JPanel(new GridLayout(8, 2));
		add(attributesPnl, constraints);
		attributesPnl.add(costLbl);
		attributesPnl.add(costTxtFld);
		attributesPnl.add(damageLbl);
		attributesPnl.add(damageTxtFld);
		attributesPnl.add(rangeLbl);
		attributesPnl.add(rangeTxtFld);
		attributesPnl.add(levelLbl);
		attributesPnl.add(levelTxtFld);
		attributesPnl.add(fireRateLbl);
		attributesPnl.add(fireRateTxtFld);
		attributesPnl.add(refundLbl);
		attributesPnl.add(refundTxtFld);
		attributesPnl.add(upgradeCostLbl);
		attributesPnl.add(upgradeCostTxtFld);
		attributesPnl.add(upgradeBtn);
		attributesPnl.add(sellBtn);


		//		constraints.gridx = 0;
		constraints.gridy = 1;
		add(weakestStratBtn, constraints);
		constraints.gridy = 2;
		add(closestStratBtn, constraints);
		constraints.gridy = 3;
		add(shootingStratBtn, constraints);

		costTxtFld.setEditable(false);
		damageTxtFld.setEditable(false);
		rangeTxtFld .setEditable(false);
		levelTxtFld .setEditable(false);
		refundTxtFld .setEditable(false);
		upgradeCostTxtFld .setEditable(false);
	}

	/**
	 * Update the view in {@link TowerPanel}
	 * @param o is ignored
	 * @param arg is ignored
	 */
	@Override
	public void update(Observable o, Object arg) {
		read(shownTower);

	}

	/**
	 * Sets the {@link Tower} to have its information displayed
	 * @param tower {@link Tower} to have its information displayed
	 */
	public void show(Tower tower) {
		if (shownTower != null) {
			shownTower.deleteObserver(this);
		}
		shownTower = tower;
		tower.addObserver(this);
		read(tower);
	}

	/**
	 * Reads into the {@link TextField}s the {@link Tower}'s information
	 * @param tower {@link Tower} to have its information read
	 */
	private void read(Tower tower) {
		costTxtFld.setText("" + tower.getBuyCost());
		damageTxtFld.setText("" + tower.getDamage());
		rangeTxtFld .setText("" + tower.getRange());
		levelTxtFld .setText("" + tower.getLevel());
		fireRateTxtFld.setText("" + tower.getShootRateSecs());
		refundTxtFld.setText("" + tower.getRefundRate());
		if (tower.canUpgrade()) {
			upgradeCostTxtFld .setText("" + tower.getUpgradeCost());
			upgradeBtn.setEnabled(true);
		}
		else {
			upgradeCostTxtFld .setText("N/A");
			upgradeBtn.setEnabled(false);
		}
	}

	/**
	 * Returns the upgrade button
	 * @return the upgrade button
	 */
	public JButton getUpgradeBtn() {
		return upgradeBtn;
	}

	/**
	 * Returns the sell button
	 * @return the sell button
	 */
	public JButton getSellBtn() {
		return sellBtn;
	}

	/**
	 * Returns the weakest strategy button
	 * @return the weakest strategy button
	 */
	public JButton getWeakestStratBtn() {
		return weakestStratBtn;
	}

	/**
	 * Returns the closest strategy button
	 * @return the closest strategy button
	 */
	public JButton getClosestStratBtn() {
		return closestStratBtn;
	}

	/**
	 * Returns the shooting strategy  button
	 * @return the shooting strategy  button
	 */
	public JButton getShootingStratBtn() {
		return shootingStratBtn;
	}
	/**
	 * Sets the {@link Tower} to be shown by this {@link TowerPanel}
	 * @param shownTower {@link Tower} to be shown by this {@link TowerPanel}
	 */
	public void setShownTower(Tower shownTower) {
		this.shownTower = shownTower;
	}











}
