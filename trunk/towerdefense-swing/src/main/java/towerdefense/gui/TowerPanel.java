package towerdefense.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

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
	private final JLabel refundLbl = new JLabel("Refund:");
	private final JLabel upgradeCostLbl = new JLabel("Upgrade cost:");
	
	private final JTextField costTxtFld = new JTextField("");
	private final JTextField damageTxtFld = new JTextField("");
	private final JTextField rangeTxtFld = new JTextField("");
	private final JTextField levelTxtFld = new JTextField("");
	private final JTextField refundTxtFld = new JTextField("");
	private final JTextField upgradeCostTxtFld = new JTextField("");
	
	private final JButton upgradeBtn = new JButton("Upgrade");
	private final JButton sellBtn = new JButton("Sell");
	
	private Tower shownTower = null;


	/**
	 * Constructs a {@link TowerPanel}
	 */
	public TowerPanel()
	{
		super(new GridLayout(7, 2));
		setMinimumSize(new Dimension(200, 350));
		setMaximumSize(new Dimension(200, 350));
		add(costLbl);
		add(costTxtFld);
		add(damageLbl);
		add(damageTxtFld);
		add(rangeLbl);
		add(rangeTxtFld);
		add(levelLbl);
		add(levelTxtFld);
		add(refundLbl);
		add(refundTxtFld);
		add(upgradeCostLbl);
		add(upgradeCostTxtFld);
		add(upgradeBtn);
		add(sellBtn);
		
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
	 * Sets the {@link Tower} to be shown by this {@link TowerPanel}
	 * @param shownTower {@link Tower} to be shown by this {@link TowerPanel}
	 */
	public void setShownTower(Tower shownTower) {
		this.shownTower = shownTower;
	}
	
	









}
