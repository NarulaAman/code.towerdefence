package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GameMapDao;
import ca.concordia.soen6441.logic.MapValidator;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This dialog is responsible for the MapEdition, where the user will be able to edit and save his maps
 *
 */
public class MapEditionDialog extends JDialog implements MapGridCoordinateClickedListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9131842957154665067L;
	private static final Icon ENTRY_ICON = new ImageIcon(Object.class.getResource("/icons/entry.png"));
	private static final Icon EXIT_ICON = new ImageIcon(Object.class.getResource("/icons/exit.png"));
	private static final Icon PATH_ICON = new ImageIcon(Object.class.getResource("/icons/path.png"));
	private static final Icon SCENERY_BUTTON_ICON = new ImageIcon(Object.class.getResource("/icons/scenery.png"));
	private static final Icon SAVE_ICON = new ImageIcon(Object.class.getResource("/icons/save.png"));

	private final MapPanel gridPanel = new MapPanel();

	private final JButton entryButton = new JButton(ENTRY_ICON);
	private final JButton exitButton= new JButton(EXIT_ICON);
	private final JButton pathButton = new JButton(PATH_ICON);
	private final JButton sceneryButton = new JButton(SCENERY_BUTTON_ICON);
	private final JButton saveButton = new JButton(SAVE_ICON);
	private final JTextField nameMapText = new JTextField("DefaultMap");
	
	private final MapValidator mapValidator = new MapValidator();
	
	private final GameMapDao gameMapDao;
	
    private GameMap gameMap;
    
    private StartGameDialog startGameDialog;

	/**
	 * Enum which holds the internal state of this dialog
	 *
	 */
	private enum SelectedButton {
		/**
		 * Scenery being set on the map
		 */
		SCENERY,
		/**
		 * Enemy path being set on the map
		 */
		ENEMY_PATH,
		/**
		 * Entry being set on the map
		 */
		ENTRY,
		/**
		 * Exit being set on the map
		 */
		EXIT
	}
	
	private SelectedButton selectedButton = SelectedButton.SCENERY;

	/**
	 * Creates a MapEditionDialog with a given {@link GameMapDao}
	 * @param gameMapDao {@link GameMapDao} to have the maps loaded from
	 */
	public MapEditionDialog(GameMapDao gameMapDao) {
		setLayout(new BorderLayout());
		setResizable(false);
		this.gameMapDao = gameMapDao;
		add(gridPanel,BorderLayout.CENTER);
		gridPanel.addMapGridCoordinateClickedListener(this);
		setupButtons();
		JPanel nameMapPanel = new JPanel(new GridLayout(1,2,2,2));
		JLabel nameMapLabel = new JLabel("Please Enter the GameMap Name");
		nameMapPanel.add(nameMapLabel);
		nameMapPanel.add(nameMapText);
		add(nameMapPanel,BorderLayout.SOUTH);
		pack();
	}


	/**
	 * Setup the buttons of this dialog
	 */
	private void setupButtons() {
		layoutButtons();
		addBehaviorToButtons();
	}


	/**
	 * Layout the buttons
	 */
	private void layoutButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(entryButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(pathButton);
		buttonPanel.add(sceneryButton);		
		buttonPanel.add(saveButton);
		add(buttonPanel,BorderLayout.EAST);
		saveButton.setVisible(true);
	}

	public void setStartGameDialog(StartGameDialog startGameDialog) {
		this.startGameDialog = startGameDialog;
	}
	
	/**
	 * Saves the {@link GameMap} being currently opened in the Edition Panel
	 * @return true if the map was saved
	 */
	public boolean saveMap() {
		try {
			if (nameMapText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Map name can't be empty");
				return false;
			}
			StringBuilder incorrectMap = new StringBuilder("");
			Boolean mapValidate = mapValidator.isValid(gameMap, incorrectMap);
			if(mapValidate) {
				startGameDialog.updateGameMap(gameMap);
				gameMap.setName(nameMapText.getText());
				gameMapDao.save(gameMap);
				this.setVisible(false);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, incorrectMap);
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;	
		}
		
	}
	
	/**
	 * Add the action listeners to the buttons
	 */
	private void addBehaviorToButtons() {
		

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.EXIT;
			}
		});

		pathButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.ENEMY_PATH;
			}
		});

		sceneryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.SCENERY;
			}
		});
		
		entryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.ENTRY;
			}
		});
		
	}


	/**
	 * Sets the map to be edited
	 * @param gameMap {@link GameMap} to be edited
	 */
	public void setMap(GameMap gameMap) {
		this.gameMap = gameMap;
		nameMapText.setText(gameMap.getName());
		gridPanel.setMap(gameMap);
	}
    
	/**
	 * Method triggered when position is clicked to change this position to be: path, scenery, entry or exit
	 * @param gridPosition position that being clicked
	 */
	@Override
	public void mapGridCoordinateClicked(GridPosition gridPosition) {
		//				

		if (selectedButton == SelectedButton.ENEMY_PATH) {
			gameMap.setTile(gridPosition, Tile.ENEMY_PATH);
		}
		if(selectedButton == SelectedButton.SCENERY) {
			gameMap.setTile(gridPosition, Tile.SCENERY);
		}
		if(selectedButton == SelectedButton.ENTRY) {
			gameMap.setStartGridPosition(gridPosition);
			//gameMap.setTile(x, y, Tile.TileType.ENEMY_PATH);
		}
		if(selectedButton == SelectedButton.EXIT) {
			gameMap.setEndGridPosition(gridPosition);
			//gameMap.setTile(x, y, Tile.TileType.ENEMY_PATH);
		}
	}
	
	/**
	 * Returns the save button
	 * @return the save button
	 */
	public JButton getSaveButton() {
		return saveButton;
	}
	
//	/**
//	 * Creates the GUI for testing purposes
//	 */
//	private static void createAndShowGUI() {
//		GameMap gameMap = new GameMap(4, 4);
//		MapEditionDialog mapEditionPanel = new MapEditionDialog(new GameMapJavaSerializationDao());
//		mapEditionPanel.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		mapEditionPanel.setMap(gameMap);
//		mapEditionPanel.setVisible(true);
//	}
//
//	/**
//	 * Main method used for testing the GUI
//	 * @param args arguments are ignored by this method
//	 */
//	public static void main(String[] args) {
//		//Schedule a job for the event-dispatching thread:
//		//creating and showing this application's GUI.
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createAndShowGUI();
//			}
//		});
//	}

}
