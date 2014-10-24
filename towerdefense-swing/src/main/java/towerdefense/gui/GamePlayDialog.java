package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import towerdefense.gui.GamePlayPanel.TowerSelectedListener;
import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This is the dialog which will show the {@link GamePlay}. Here the user will be able to play the game
 *
 */
public class GamePlayDialog extends JDialog implements TowerSelectedListener, MapGridCoordinateClickedListener, Observer{
	
	private enum State {
		NOTHING,
		BUYING_TOWER
	}
	private final GamePlayPanel gamePlayPanel;

	private final JLabel livesLbl = new JLabel("Lives");
	private final JLabel scoreLbl = new JLabel("Scores");
	private final JLabel levelsLbl = new JLabel("Levels");
	private final JLabel banksLbl = new JLabel("Banks");

	private final JTextField livesTxtFld = new JTextField("");
	private final JTextField scoreTxtFld = new JTextField("");
	private final JTextField levelsTxtFld = new JTextField("");
	private final JTextField banksTxtFld = new JTextField("");
	
	private final TowerPanel towerInspectionPanel = new TowerPanel();
	
	private final JButton buyTowerBtn = new JButton("Buy Tower");
	private final TowerFactory towerFactory = new TowerFactory();
	
	private Class<? extends Tower> towerToBuy = null;
	private Tower selectedTower = null;
	
	private State state = State.NOTHING;

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
		buyTowerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				towerToBuy = Tower.class;
				towerInspectionPanel.setVisible(false);
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
		towersToBuyPanel.add(buyTowerBtn);
		buyTowerBtn.setToolTipText(towerFactory.getLevelInformation(Tower.class, 1).toHtmlString());
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

		livesTxtFld.setEditable(false);
		scoreTxtFld.setEditable(false);
		levelsTxtFld.setEditable(false);
		banksTxtFld.setEditable(false);
	
		gamePlayAttributes.add(livesLbl);
		gamePlayAttributes.add(livesTxtFld);
		gamePlayAttributes.add(scoreLbl);
		gamePlayAttributes.add(scoreTxtFld);
		gamePlayAttributes.add(levelsLbl);
		gamePlayAttributes.add(levelsTxtFld);
		gamePlayAttributes.add(banksLbl);
		gamePlayAttributes.add(banksTxtFld);
		
		sideBar.add(gamePlayAttributes);
		
	}

	/* (non-Javadoc)
	 * @see towerdefense.gui.MapPanel.MapGridCoordinateClickedListener#mapGridCoordinateClicked(ca.concordia.soen6441.logic.primitives.GridPosition)
	 */
	@Override
	public void mapGridCoordinateClicked(GridPosition gridPosition) {
		if (state == State.BUYING_TOWER) {
			Tower tower = towerFactory.towerOnCoordinate(towerToBuy, gridPosition);
			getGamePlay().buy(tower);
//			state = State.NOTHING;
		}
		
	}

	/* (non-Javadoc)
	 * @see towerdefense.gui.GamePlayPanel.TowerSelectedListener#towerSelected(ca.concordia.soen6441.logic.Tower)
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
		banksTxtFld.setText("" + gamePlay.getCurrency());
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		readGamePlay();
		
	}
	
	@Override
	public void dispose() {
		gamePlayPanel.dispose();
		super.dispose();
	}
	
	/**
	 * Creates the GUI for testing purposes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static void createAndShowGUI() throws ClassNotFoundException, IOException {
		//Create and set up the window.
		JFrame frame = new JFrame("GamePlayPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameMapDao gameMapDao = new GameMapJavaSerializationDao();
		GameMap gameMap = gameMapDao.load("DefaultMap");
		GamePlay gamePlay = new GamePlay(gameMap, 1000);
		GamePlayDialog gamePlayPanel = new GamePlayDialog(gamePlay);
		frame.setContentPane(gamePlayPanel);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
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
