package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import towerdefense.gui.GamePlayPanel.TowerSelectedListener;
import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import towerdefense.gui.log.GameLogDialog;
import ca.concordia.soen6441.dao.GamePlayDao;
import ca.concordia.soen6441.dao.MapLoggerDao;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.io.MapLoggerJavaSerializationDao;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.CannonTower;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.IceTower;
import ca.concordia.soen6441.logic.tower.Tower;
import ca.concordia.soen6441.logic.tower.TowerFactory;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootClosestToEndPointStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootClosestToTowerStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootStrongestStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootWeakestStrategy;

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
	private final JLabel banksLabel = new JLabel("Banks");
	private final JLabel scoresLabel = new JLabel("Scores");
	private final JLabel levelsLabel = new JLabel("Wave");

	private final JTextField livesField = new JTextField("");
	private final JTextField banksField = new JTextField("");
	private final JTextField scoresField = new JTextField("");
	private final JTextField levelsField = new JTextField("");

	private final TowerPanel towerInspectionPanel = new TowerPanel();

	private final JButton saveGamePlayBtn = new JButton("Save Game");	
	private final JButton fireTowerButton = new JButton("Fire Tower");
	private final JButton iceTowerButton = new JButton("Ice Tower");
	private final JButton cannonTowerButton = new JButton("Cannon Tower");
	private final JButton startGameButton = new JButton("Start Game");
	private final JButton gamePlayLogButton = new JButton("Game Play Log");
	
	private final TowerFactory towerFactory;
	
	private java.util.List<Component> towerButtons = new ArrayList<>();

	private Timer gameplayUpdateTimer = new Timer();
	
	private Class<? extends Tower> towerToBuy = null;
	
	private Tower selectedTower = null;

	private State state = State.NOTHING;
	private BuyingTower buyingTower = BuyingTower.FIRE_TOWER;
	
	private int level = 1;

	private GamePlay gamePlay;
	
	private GamePlayDao gamePlayDao = new GamePlayJavaSerialaizationDao();

	private MapLoggerDao mapLoggerDao = new MapLoggerJavaSerializationDao();
	
	private boolean showHighScoresEnd = true;

	/**
	 * Constructs a {@link GamePlayDialog} to play an instance of {@link GamePlay}
	 * @param gamePlay {@link GamePlay} to be played in this dialog
	 */
	public GamePlayDialog(GamePlay gamePlay) {
		setLayout(new BorderLayout());
		setResizable(false);
		this.gamePlay = gamePlay;
		this.towerFactory = gamePlay.getTowerFactory();
		gamePlay.addObserver(this);
		gamePlayPanel = new GamePlayPanel(gamePlay);
		gamePlayPanel.setMapGridCoordinateClickedListener(this);
		gamePlayPanel.setTowerSelectedListener(this);
		add(gamePlayPanel, BorderLayout.CENTER);
		setupSidebar();
		towerButtons.add(fireTowerButton);
		towerButtons.add(iceTowerButton);
		towerButtons.add(cannonTowerButton);

		towerInspectionPanel.setVisible(false);
		
		readGamePlay();
		//		startGamePlayUpdaateTimer();
		pack();
		setVisible(true);
		
		if (gamePlay.getLevel() == 1) {
			showHighScores();
		}
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
		
		saveGamePlayBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gamePlayDao.save(gamePlay);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		add(sideBar, BorderLayout.EAST);
	}	

	/**
	 * Setup the BuyTower button
	 */
	private void setupBuyTowerButton() {
		fireTowerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				buyingTower = BuyingTower.FIRE_TOWER;
				selectTowerToBuy();
				towerInspectionPanel.setVisible(false);
			}
		});
		iceTowerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				buyingTower = BuyingTower.ICE_TOWER;
				selectTowerToBuy();
				towerInspectionPanel.setVisible(false);
			}
		});
		cannonTowerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				buyingTower = BuyingTower.CANNON_TOWER;
				selectTowerToBuy();
				towerInspectionPanel.setVisible(false);
			}
		});
	}

	/**
	 * Setup the tower to be bought
	 * @return true if there was a tower to be bought, false if not
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

	/**
	 * Starts the gameplay timer to update the simulation
	 */
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
						update(seconds);
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
	 * Update the {@link GamePlay}
	 * @param seconds seconds passed
	 */
	public void update(float seconds) {
		gamePlay.update(seconds);
		//		System.out.println("Seconds: " + seconds);
	}

	/**
	 * Sets up the TowerInspectionWindow on the SideBar
	 * @param sideBar side bar to have the tower inspection window added to
	 */
	private void setupInspectionWindow(JPanel sideBar) {
		sideBar.add(towerInspectionPanel);

//        	Enumeration<AbstractButton> buttons = buttonGroup.getElements();
//        	while(buttons.hasMoreElements()){
//        		if(buttons.nextElement().isSelected()) {
//        			buttons.nextElement().setEnabled(false);
//        		}
//        		
//        		
//        	}      		       	
        
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
				if(!gamePlay.tryToUpgrade(selectedTower)) {
					towerInspectionPanel.getUpgradeBtn().setEnabled(false);
				}
			}
		});
		
		
		towerInspectionPanel.getShootStrongestStratBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTower.setShootingStrategy(new ShootStrongestStrategy());				
			}
		});
		
		towerInspectionPanel.getShootClosestToTowerStratBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTower.setShootingStrategy(new ShootClosestToTowerStrategy());
			}
		});
		
		towerInspectionPanel.getShootClosestToEndPointStratBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTower.setShootingStrategy(new ShootClosestToEndPointStrategy());
			}
		});
		
		towerInspectionPanel.getShootWeakestStratBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTower.setShootingStrategy(new ShootWeakestStrategy());
			}
		});
	}

	/**
	 * Setup the panel with towers that can be bought
	 * @param sideBar sidebar to have the the panel added to
	 */
	private void setupTowerAvailableToBuyPanel(JPanel sideBar) {
		JPanel towersToBuyPanel = new JPanel();
		towersToBuyPanel.setMaximumSize(new Dimension(200, 120));
		towersToBuyPanel.setMinimumSize(new Dimension(200, 120));
		towersToBuyPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		setMinimumSize(new Dimension(200, 350));
		setMaximumSize(new Dimension(200, 350));
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = constraints.weighty = 1.0;
		
		towersToBuyPanel.add(startGameButton, constraints);
		constraints.gridy = 1;
		towersToBuyPanel.add(fireTowerButton,constraints);
		constraints.gridy = 2;
		towersToBuyPanel.add(iceTowerButton,constraints);
		constraints.gridy = 3;
		towersToBuyPanel.add(cannonTowerButton,constraints);
		constraints.gridy = 4;
		towersToBuyPanel.add(gamePlayLogButton,constraints);
		constraints.gridy = 5;
		towersToBuyPanel.add(saveGamePlayBtn,constraints);
		
		fireTowerButton.setToolTipText(towerFactory.getLevelInformation(FireTower.class, 1).toHtmlString());
		iceTowerButton.setToolTipText(towerFactory.getLevelInformation(IceTower.class, 1).toHtmlString());
		cannonTowerButton.setToolTipText(towerFactory.getLevelInformation(CannonTower.class, 1).toHtmlString());
		
		startGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePlay.start();
				gameplayUpdateTimer = new Timer();
				startGamePlayUpdaateTimer();
				level++;
			}
		});
		
		
		gamePlayLogButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		    GameLogDialog log = new GameLogDialog(gamePlay.getLogger());
				
			}
		});
		 // this is a hack to increase the size of enemies at every wave, remove in the future!!
		sideBar.add(towersToBuyPanel);

	}

	/**
	 * Sets up the {@link GamePslay} attributes
	 * @param sideBar sidebar to have the attributes added to
	 */
	private void setupGamePlayAttributes(JPanel sideBar) {

		JPanel gamePlayAttributes = new JPanel(new GridLayout(2,4));
		gamePlayAttributes.setPreferredSize(new Dimension(200, 50));
		gamePlayAttributes.setMaximumSize(new Dimension(250, 50));

		livesField.setEditable(false);
		banksField.setEditable(false);

		gamePlayAttributes.add(livesLabel);
		gamePlayAttributes.add(livesField);
		gamePlayAttributes.add(banksLabel);
		gamePlayAttributes.add(banksField);
		gamePlayAttributes.add(scoresLabel);
		gamePlayAttributes.add(scoresField);
		gamePlayAttributes.add(levelsLabel);
		gamePlayAttributes.add(levelsField);

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
			getGamePlay().tryToBuy(tower);
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
		if (gamePlay.isStateGameOver() || gamePlay.isStateSetup()) {
			gameplayUpdateTimer.cancel();
		}


		enableButtons();
		disableButtons();
		banksField.setText("" + gamePlay.getCurrency());
		livesField.setText("" + gamePlay.getLives());
		scoresField.setText("" + gamePlay.getScore());
		levelsField.setText("" + gamePlay.getLevel());
		
		if (gamePlay.isStateGameOver() && showHighScoresEnd) {			
			showHighScores();
			showHighScoresEnd = false;
		}
	}

	/**
	 * Disable the buttons
	 */
	private void disableButtons() {
		if(gamePlay.isStateRunning()) {
			for(Component component : towerButtons) {
			    component.setEnabled(false);
			} 
			startGameButton.setEnabled(false);
			towerInspectionPanel.setEnabled(false);
			saveGamePlayBtn.setEnabled(false);
		}
	}

	/**
	 * Enable the buttons
	 */
	private void enableButtons() {
		if(gamePlay.isStateSetup() || gamePlay.isStateGameOver()) {
			for(Component component : towerButtons) {
			    component.setEnabled(true);
			}
			startGameButton.setEnabled(true);
			towerInspectionPanel.setEnabled(true);
			saveGamePlayBtn.setEnabled(true);
		}
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
	
	
	/**
	 * Display the high scores
	 */
	private void showHighScores() {
		String highScoresMessage = "High scores:\n";
		for (Integer score : gamePlay.getHighScores().getScoreList()) {
			highScoresMessage += "" + score + "\n";
		}
		JOptionPane.showMessageDialog(null,
				highScoresMessage);
	}	

	//	/**
	//	 * Creates the GUI for testing purposes
	//	 * @throws ClassNotFoundException
	//	 * @throws IOException
	//	 */
		private static void createAndShowGUI() throws ClassNotFoundException, IOException {
			//Create and set up the window.
//			JFrame frame = new JFrame("GamePlayPanel");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			GamePlayDao gameMapDao = new GamePlayJavaSerialaizationDao();
			GamePlay gamePlay = gameMapDao.load("gamePlay1");
			GamePlayDialog gamePlayPanel = new GamePlayDialog(gamePlay);
//			frame.setContentPane(gamePlayPanel);
//	
//			//Display the window.
//			frame.pack();
//			frame.setVisible(true);
		}
	
		/**
		 * Main method used for testing the GUI
		 * @param args arguments are ignored by this method
		 */
		public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						createAndShowGUI();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		

}
