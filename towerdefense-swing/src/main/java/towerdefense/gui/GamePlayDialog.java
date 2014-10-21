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

import towerdefense.gui.GamePanel.TowerSelectedListener;
import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.io.MapPersister;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class GamePlayDialog extends JDialog implements TowerSelectedListener, MapGridCoordinateClickedListener, Observer{
	
	private enum State {
		NOTHING,
		BUYING_TOWER
	}
	private final GamePanel gamePlayPanel;

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
	public GamePlayDialog(GamePlay gamePlay) {
		setLayout(new BorderLayout());
		this.gamePlay = gamePlay;
		gamePlay.addObserver(this);
		gamePlayPanel = new GamePanel(gamePlay);
		gamePlayPanel.setMapGridCoordinateClickedListener(this);
		gamePlayPanel.setTowerSelectedListener(this);

		add(gamePlayPanel, BorderLayout.CENTER);
		setupSidebar();
		towerInspectionPanel.setVisible(false);
		readGamePlay();
		pack();
	}

	private void setupSidebar() {
		
		
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
		setupGamePlayAttributes(sideBar);
		
		setupTowerAvailableToBuyPanel(sideBar);

		setupInspectionWindow(sideBar);

		setupButtons();
		
		
		add(sideBar, BorderLayout.EAST);
	}	

	private void setupButtons() {
		buyTowerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state = State.BUYING_TOWER;
				towerToBuy = Tower.class;
				towerInspectionPanel.setVisible(false);
			}
		});
		
	}

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

	private void setupTowerAvailableToBuyPanel(JPanel sideBar) {
		JPanel towersToBuyPanel = new JPanel();
		towersToBuyPanel.add(buyTowerBtn);
		buyTowerBtn.setToolTipText(towerFactory.getLevelInformation(Tower.class, 1).toHtmlString());
		sideBar.add(towersToBuyPanel);
		
	}

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

	private static void createAndShowGUI() throws ClassNotFoundException, IOException {
		//Create and set up the window.
		JFrame frame = new JFrame("GamePlayPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MapPersister mapPersister = new MapJavaSerializationPersister();
		Map map = mapPersister.load("DefaultMap");
		GamePlay gamePlay = new GamePlay(map, 1000);
		GamePlayDialog gamePlayPanel = new GamePlayDialog(gamePlay);
		frame.setContentPane(gamePlayPanel);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

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


	@Override
	public void mapGridCoordinateClicked(int x, int y) {
		if (state == State.BUYING_TOWER) {
			Tower tower = towerFactory.towerOnCoordinate(towerToBuy, new Coordinate(x, y));
			getGamePlay().buy(tower);
//			state = State.NOTHING;
		}
		
	}

	@Override
	public void towerSelected(Tower tower) {
		selectedTower = tower;
		towerInspectionPanel.show(selectedTower);
		towerInspectionPanel.setVisible(true);
	}
	
	public GamePlay getGamePlay() {
		return gamePlay;
	}
	
	private void readGamePlay() {
		banksTxtFld.setText("" + gamePlay.getCurrency());
	}

	@Override
	public void update(Observable o, Object arg) {
		readGamePlay();
		
	}
}
