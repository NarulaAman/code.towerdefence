package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import towerdefense.gui.GamePlayPanel.TowerSelectedListener;
import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.IceTower;
import ca.concordia.soen6441.logic.tower.CannonTower;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * This is the dialog which will show the {@link GamePlay}. Here the user will be able to play the game
 *
 */
public class GamePlayDialog extends JDialog implements TowerSelectedListener, MapGridCoordinateClickedListener, Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2925833637083353736L;

	/**
	 * Internal state of the operations of the {@link GamePlayDialog}
	 *
	 */
	private enum State {
		/**
		 * {@link GamePlayDialog} is doing thing
		 */
		NOTHING,
		
		/**
		 * {@link GamePlayDialog} is buying tower
		 */
		BUYING_TOWER
	}
	private enum BuyingTower {
		
		/**
		 * {@link GamePlayDialog} is buying fire tower
		 */
		FIRE_TOWER,
		/**
		 * {@link GamePlayDialog} is buying ice tower
		 */
		ICE_TOWER,
		/**
		 * {@link GamePlayDialog} is buying cannon tower
		 */
		CANNON_TOWER
		
	}
	private final GamePlayPanel gamePlayPanel;

	private final JLabel livesLabel = new JLabel("Lives");
	private final JLabel scoreLabel = new JLabel("Scores");
	private final JLabel levelsLabel = new JLabel("Levels");
	private final JLabel banksLabel = new JLabel("Banks");

	private final JTextField livesField = new JTextField("");
	private final JTextField scoreField = new JTextField("");
	private final JTextField levelsField = new JTextField("");
	private final JTextField banksField = new JTextField("");
	
	private final TowerPanel towerInspectionPanel = new TowerPanel();
	
	private final JButton buyTowerButton = new JButton("Buy Tower");
	private final TowerFactory towerFactory = new TowerFactory();
	
	private final Timer gameplayUpdateTimer = new Timer();
	
	private Class<? extends Tower> towerToBuy = null;
	private Tower selectedTower = null;
	
	private State state = State.NOTHING;
	private BuyingTower buyingTower = BuyingTower.FIRE_TOWER;

	private GamePlay gamePlay;
	
	
	/**
	 * Constructs a {@link GamePlayDialog} to play an instance of {@link GamePlay}
	 * @param gamePlay {@link GamePlay} to be played in this dialog
	 */
	public GamePlayDialog(GamePlay gamePlay) {
		setLayout(new BorderLayout());
		this.gamePlay = gamePlay;
		gamePlay.addObserver(this);
		gamePlayPanel = new GamePlayPanel(gamePlay);
		gamePlayPanel.setMapGridCoordinateClickedListener(this);
		gamePlayPanel.setTowerSelectedListener(this);
		add(gamePlayPanel, BorderLayout.CENTER);
		setupSidebar();
		towerInspectionPanel.setVisible(false);
		readGamePlay();
		startGamePlayUpdaateTimer();
		pack();
	}

	/**
	 * Sets up the sidebar of the dialog
	 */
	private void setupSidebar() {
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
		setupGamePlayAttributes(sideBar);
		
		setupTowerAvailableToBuyPanel(sideBar);

		setupInspectionWindow(sideBar);

		setupBuyTowerButton();
		
		add(sideBar, BorderLayout.EAST);
	}	

	/**
	 * Setup the BuyTower button
	 */
	private void setupBuyTowerButton() {
		buyTowerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				selectTowerToBuy();
				towerToBuy = FireTower.class;
				towerInspectionPanel.setVisible(false);
			}
		});
	}
	
	/**
	 * Setup the tower to be bought
	 */
	public boolean selectTowerToBuy() {
		if(buyingTower == BuyingTower.FIRE_TOWER) {
			towerToBuy = FireTower.class;
			return true;
		}
		if(buyingTower == BuyingTower.ICE_TOWER) {
			towerToBuy = IceTower.class;
			return true;
		}
		if(buyingTower == BuyingTower.CANNON_TOWER) {
			towerToBuy = CannonTower.class;
			return true;
		}
		return false;
	}
	
	private void startGamePlayUpdaateTimer() {
		gameplayUpdateTimer.schedule(new TimerTask() {
			
			long lastTimestamp = System.currentTimeMillis();
			
			@Override
			public void run() {
				long currentTimestamp = System.currentTimeMillis();
				long deltaTime = currentTimestamp - lastTimestamp;
				final float seconds = (float) deltaTime / 1000;
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						gamePlay.update(seconds);
						System.out.println("Seconds: " + seconds);
					}
				});
				lastTimestamp = currentTimestamp;
			}
		}, 100, 100);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				gameplayUpdateTimer.cancel();
			}
		});
	}

	/**
	 * Sets up the TowerInspectionWindow on the SideBar
	 * @param sideBar side bar to have the tower inspection window added to
	 */
	private void setupInspectionWindow(JPanel sideBar) {
		sideBar.add(towerInspectionPanel);
		
		towerInspectionPanel.getSellBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePlay.sell(selectedTower);
				towerInspectionPanel.setVisible(false);
			}
		});
		
		towerInspectionPanel.getUpgradeBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePlay.upgrade(selectedTower);
			}
		});
	}

	/**
	 * Setup the panel with towers that can be bought
	 * @param sideBar sidebar to have the the panel added to
	 */
	private void setupTowerAvailableToBuyPanel(JPanel sideBar) {
		JPanel towersToBuyPanel = new JPanel();
		towersToBuyPanel.add(buyTowerButton);
		buyTowerButton.setToolTipText(towerFactory.getLevelInformation(FireTower.class, 1).toHtmlString());
		sideBar.add(towersToBuyPanel);
		
	}

	/**
	 * Sets up the {@link GamePlay} attributes
	 * @param sideBar sidebar to have the attributes added to
	 */
	private void setupGamePlayAttributes(JPanel sideBar) {

		JPanel gamePlayAttributes = new JPanel(new GridLayout(2,4));
		gamePlayAttributes.setPreferredSize(new Dimension(200, 50));
		gamePlayAttributes.setMaximumSize(new Dimension(250, 50));

		livesField.setEditable(false);
		scoreField.setEditable(false);
		levelsField.setEditable(false);
		banksField.setEditable(false);
	
		gamePlayAttributes.add(livesLabel);
		gamePlayAttributes.add(livesField);
		gamePlayAttributes.add(scoreLabel);
		gamePlayAttributes.add(scoreField);
		gamePlayAttributes.add(levelsLabel);
		gamePlayAttributes.add(levelsField);
		gamePlayAttributes.add(banksLabel);
		gamePlayAttributes.add(banksField);
		
		sideBar.add(gamePlayAttributes);
		
	}

	/**
	 * Notifies that a {@link GridPosition} was clicked on the Map
	 * @param gridPosition position clicked
	 */
	@Override
	public void mapGridCoordinateClicked(GridPosition gridPosition) {
		if (state == State.BUYING_TOWER) {
			Tower tower = towerFactory.towerOnCoordinate(towerToBuy, gridPosition);
			getGamePlay().buy(tower);
//			state = State.NOTHING;
		}
		
	}

	
	/**
	 * Notifies that a {@link Tower} was selected on the Map
	 * @param tower the tower selected
	 */
	@Override
	public void towerSelected(Tower tower) {
		selectedTower = tower;
		towerInspectionPanel.show(selectedTower);
		towerInspectionPanel.setVisible(true);
	}
	
	/**
	 * Returns the current {@link GamePlay} instance 
	 * @return the current {@link GamePlay} instance 
	 */
	public GamePlay getGamePlay() {
		return gamePlay;
	}
	
	/**
	 * Reads the current {@link GamePlay} attributes
	 */
	private void readGamePlay() {
		banksField.setText("" + gamePlay.getCurrency());
	}

	/**
	 * Update the current {@link GamePlay}
	 * @param o ignored - not used
	 * @param arg ignored - not used
	 */
	@Override
	public void update(Observable o, Object arg) {
		readGamePlay();		
	}
	
	/**
	 * Clean up method
	 */
	@Override
	public void dispose() {
		gameplayUpdateTimer.cancel();
		gamePlayPanel.dispose();
		super.dispose();
	}
	
//	/**
//	 * Creates the GUI for testing purposes
//	 * @throws ClassNotFoundException
//	 * @throws IOException
//	 */
//	private static void createAndShowGUI() throws ClassNotFoundException, IOException {
//		//Create and set up the window.
//		JFrame frame = new JFrame("GamePlayPanel");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		GameMapDao gameMapDao = new GameMapJavaSerializationDao();
//		GameMap gameMap = gameMapDao.load("DefaultMap");
//		GamePlay gamePlay = new GamePlay(gameMap, 1000);
//		GamePlayDialog gamePlayPanel = new GamePlayDialog(gamePlay);
//		frame.setContentPane(gamePlayPanel);
//
//		//Display the window.
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//	/**
//	 * Main method used for testing the GUI
//	 * @param args arguments are ignored by this method
//	 */
//	public static void main(String[] args) {
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					createAndShowGUI();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
