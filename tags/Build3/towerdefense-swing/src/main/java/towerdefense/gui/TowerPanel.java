package towerdefense.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ca.concordia.soen6441.logic.tower.CannonTower;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.IceTower;
import ca.concordia.soen6441.logic.tower.Tower;
import ca.concordia.soen6441.logic.tower.TowerVisitor;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootClosestToEndPointStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootClosestToTowerStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootStrongestStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootWeakestStrategy;

/**
 * This is the TowerInspectionWindow, it shows the current tower attributes
 */
public class TowerPanel extends JPanel implements Observer, TowerVisitor{

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
	
	private final List<Component> specializedTowerAttributes = new ArrayList<>();
	
	// Cannon tower
	private final JLabel splashRadiusLbl = new JLabel("Splash radius:");
	private final JLabel splashDamageRatioLbl = new JLabel("Splash damage ratio:");
	
	private final JTextField splashRadiusTxtFld = new JTextField("");
	private final JTextField splashDamageRatioTxtFld = new JTextField("");
	
	// FireTower attributes
	private final JLabel burnDamageLbl = new JLabel("Burn damage:");
	private final JLabel burnRateLbl = new JLabel("Burn every secs:");
	private final JLabel burnTimeLbl = new JLabel("Burn for secs:");
	
	private final JTextField burnDamageTxtFld = new JTextField("");
	private final JTextField burnRateRatioTxtFld = new JTextField("");
	private final JTextField burnTimeTxtFld = new JTextField("");
	
	
	// IceTower attributes
	private final JLabel slowTimeLbl = new JLabel("Slows for secs:");
	private final JTextField slowTimeTxtFld = new JTextField("");
	private final JLabel slowRateLbl = new JLabel("Speed rate:");
	private final JTextField slowRateTxtFld = new JTextField("");	
	
	private final JTextField costTxtFld = new JTextField("");
	private final JTextField damageTxtFld = new JTextField("");
	private final JTextField rangeTxtFld = new JTextField("");
	private final JTextField levelTxtFld = new JTextField("");
	private final JTextField fireRateTxtFld = new JTextField("");
	private final JTextField refundTxtFld = new JTextField("");
	private final JTextField upgradeCostTxtFld = new JTextField("");

	private final JButton upgradeBtn = new JButton("Upgrade");
	private final JButton sellBtn = new JButton("Sell");
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JToggleButton shootStrongestStratBtn = new JToggleButton("Shoot Strongest");
	private final JToggleButton shootClosestToTowerStratBtn = new JToggleButton("Shoot Closest to Tower");
	private final JToggleButton shootClosestToEndPointStratBtn = new JToggleButton("Shoot Closest to End");
	private final JToggleButton shootWeakestStratBtn = new JToggleButton("Shoot Weakest");
	
	private Tower shownTower = null;
	private JPanel attributesPnl;
	private JPanel fireTowerAttributes = new JPanel(new GridLayout(3,2));
	private JPanel iceTowerAttributes = new JPanel(new GridLayout(1,2));
	private JPanel cannonTowerAttributes = new JPanel(new GridLayout(2,2));
	GridBagConstraints constraints = new GridBagConstraints();


	/**
	 * Constructs a {@link TowerPanel}
	 */
	public TowerPanel()
	{
		super();
		setLayout(new GridBagLayout());
		
		setMinimumSize(new Dimension(200, 400));
		setMaximumSize(new Dimension(200, 400));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = constraints.weighty = 1.0;

		attributesPnl = new JPanel(new GridLayout(8, 2));
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
		add(shootStrongestStratBtn, constraints);
		constraints.gridy = 2;
		add(shootClosestToTowerStratBtn, constraints);
		constraints.gridy = 3;
		add(shootClosestToEndPointStratBtn, constraints);
		constraints.gridy = 4;
		add(shootWeakestStratBtn, constraints);

		costTxtFld.setEditable(false);
		damageTxtFld.setEditable(false);
		rangeTxtFld.setEditable(false);
		levelTxtFld.setEditable(false);
		refundTxtFld.setEditable(false);
		upgradeCostTxtFld.setEditable(false);
		
		fireTowerAttributes.add(burnRateLbl);
		fireTowerAttributes.add(burnRateRatioTxtFld);
		fireTowerAttributes.add(burnDamageLbl);
		fireTowerAttributes.add(burnDamageTxtFld);
		fireTowerAttributes.add(burnTimeLbl);
		fireTowerAttributes.add(burnTimeTxtFld);
		
		burnDamageTxtFld.setEditable(false);
		burnTimeTxtFld.setEditable(false);
		burnRateRatioTxtFld.setEditable(false);
		
		
		iceTowerAttributes.setLayout(new GridLayout(2,2));
		iceTowerAttributes.add(slowTimeLbl);
		iceTowerAttributes.add(slowTimeTxtFld);
		iceTowerAttributes.add(slowRateLbl);
		iceTowerAttributes.add(slowRateTxtFld);
		
		slowTimeTxtFld.setEditable(false);
		slowRateTxtFld.setEditable(false);
		
		cannonTowerAttributes.add(splashRadiusLbl);
		cannonTowerAttributes.add(splashRadiusTxtFld);
		cannonTowerAttributes.add(splashDamageRatioLbl);
		cannonTowerAttributes.add(splashDamageRatioTxtFld);
		
		splashDamageRatioTxtFld.setEditable(false);
		splashRadiusTxtFld.setEditable(false);
	
		addStrategyButtonsToGroup();
		
		fillSpecializedAttributesList();
		
	}

	/**
	 * Add strategy buttons to button group
	 */
	private void addStrategyButtonsToGroup() {
		buttonGroup.add(getShootStrongestStratBtn());
		buttonGroup.add(getShootClosestToTowerStratBtn());
		buttonGroup.add(getShootClosestToEndPointStratBtn());
		buttonGroup.add(getShootWeakestStratBtn());
		
	}

	/**
	 * Fill specialized attributes list
	 */
	private void fillSpecializedAttributesList() {
		specializedTowerAttributes.add(fireTowerAttributes);
		specializedTowerAttributes.add(iceTowerAttributes); 
		specializedTowerAttributes.add(cannonTowerAttributes);
	
	}
	
	/**
	 * Remove special attributes
	 */
	private void removeSpecializedAttributes() {
		for (Component component : specializedTowerAttributes) {
			remove(component);
		}
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
		selectStrategy(tower);
		removeSpecializedAttributes();
		revalidate();
		repaint();
		tower.visit(this);
	}

	/**
	 * Read the strategy from the given tower
	 * @param tower to have the strategy read from
	 */
	private void selectStrategy(Tower tower) {
		// we should prefer a visitor pattern instead of tests of instanceof,
		// but we have little time now :(
		if (tower.getShootingStrategy() instanceof ShootClosestToTowerStrategy) {
			shootClosestToTowerStratBtn.setSelected(true);
		} 
		else if (tower.getShootingStrategy() instanceof ShootClosestToEndPointStrategy) {
			shootClosestToEndPointStratBtn.setSelected(true);
		} 
		else if (tower.getShootingStrategy() instanceof ShootStrongestStrategy) {
			shootStrongestStratBtn.setSelected(true);
		}
		else if (tower.getShootingStrategy() instanceof ShootWeakestStrategy) {
			shootWeakestStratBtn.setSelected(true);
		}
		else {
			throw new RuntimeException("Unimplemented strategy!");
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
	 * Returns the strongest strategy button
	 * @return the strongest strategy button
	 */
	public JToggleButton getShootStrongestStratBtn() {
		return shootStrongestStratBtn;
	}

	/**
	 * Returns the weakest strategy button
	 * @return the weakest strategy button
	 */
	public JToggleButton getShootWeakestStratBtn() {
		return shootWeakestStratBtn;
	}
	
	/**
	 * Returns the closest strategy button
	 * @return the closest strategy button
	 */
	public JToggleButton getShootClosestToTowerStratBtn() {
		return shootClosestToTowerStratBtn;
	}

	/**
	 * Returns the shooting strategy  button
	 * @return the shooting strategy  button
	 */
	public JToggleButton getShootClosestToEndPointStratBtn() {
		return shootClosestToEndPointStratBtn;
	}
	/**
	 * Sets the {@link Tower} to be shown by this {@link TowerPanel}
	 * @param shownTower {@link Tower} to be shown by this {@link TowerPanel}
	 */
	public void setShownTower(Tower shownTower) {
		this.shownTower = shownTower;
	}

	/**
	 * Displays information about a {@link FireTower}
	 * @param tower to be displayed
	 */
	@Override
	public void visit(FireTower tower) {
	   constraints.gridy=5;
	   burnRateRatioTxtFld.setText("" + tower.getBurnRateSecs());
	   burnDamageTxtFld.setText("" + tower.getDamage());
	   burnTimeTxtFld.setText("" + tower.getBurnDurationSecs());
       add(fireTowerAttributes,constraints);
	}

	/**
	 * Displays information about a {@link FireTower}
	 * @param tower to be displayed
	 */
	@Override
	public void visit(IceTower tower) {
		constraints.gridy=5;
        slowTimeTxtFld.setText("" + tower.getSlownessDurationSecs());
        slowRateTxtFld.setText("" + tower.getSlownessRate());
		add(iceTowerAttributes,constraints);
	}

	/**
	 * Displays information about a {@link FireTower}
	 * @param tower to be displayed
	 */
	@Override
	public void visit(CannonTower tower) {	
		constraints.gridy=5;	
		splashRadiusTxtFld.setText("" + tower.getSplashRange());
		splashDamageRatioTxtFld.setText("" + tower.getSplashDamageRatio());
		add(cannonTowerAttributes,constraints);
		
	}

	/**
	 * Enables/disabled the buttons
	 * @param enabled boolean to show if the buttong should be enabled/disabled
	 */
	@Override
	public void setEnabled(boolean enabled) {
		sellBtn.setEnabled(enabled);
		upgradeBtn.setEnabled(enabled);
		
	}







}
