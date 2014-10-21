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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.io.MapPersister;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class MapEditionDialog extends JDialog implements MapGridCoordinateClickedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final MapPanel gridPanel = new MapPanel();
    private Map map;

	enum SelectedButton {
		SCENERY,
		ENEMY_PATH,
		ENTRY,
		EXIT
	}


	private JButton newMapButton = new JButton(NEW_ICON);;
	private JButton entryButton = new JButton(ENTRY_ICON);
	private JButton exitButton= new JButton(EXIT_ICON);
	private JButton pathButton = new JButton(PATH_ICON);
	private JButton sceneryButton = new JButton(SCENERY_BUTTON_ICON);
	private JButton saveButton = new JButton(SAVE_ICON);

	private static final Icon NEW_ICON = new ImageIcon("newb.png");
	private static final Icon ENTRY_ICON = new ImageIcon("entry.png");
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	private static final Icon PATH_ICON = new ImageIcon("path.png");
	private static final Icon SCENERY_BUTTON_ICON = new ImageIcon("scenery.png");
	private static final Icon SAVE_ICON = new ImageIcon("save.png");


	JTextField nameMapText = new JTextField("DefaultMap");
	
	private final MapPersister mapPersister;
	SelectedButton selectedButton = SelectedButton.SCENERY;

	public MapEditionDialog(MapPersister persister) {
		setLayout(new BorderLayout());
		
		this.mapPersister = persister;
		
		
		add(gridPanel,BorderLayout.CENTER);
		gridPanel.addMapGridCoordinateClickedListener(this);
		setupButtons();
		
		JPanel nameMapPanel = new JPanel(new GridLayout(1,2,2,2));
		JLabel nameMapLabel = new JLabel("Please Enter the Map Name");
		
		nameMapPanel.add(nameMapLabel);
		nameMapPanel.add(nameMapText);
		add(nameMapPanel,BorderLayout.SOUTH);
		
		
		pack();
	}


	private void setupButtons() {
		layoutButtons();
		addBehaviorToButtons();
		


	}


	private void layoutButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(newMapButton);
		buttonPanel.add(entryButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(pathButton);
		buttonPanel.add(sceneryButton);		
		buttonPanel.add(saveButton);
		add(buttonPanel,BorderLayout.EAST);
		if(nameMapText.getText()!=null)
		{
			saveButton.setVisible(true);
		}
	}


	private void addBehaviorToButtons() {
		newMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Game?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION);
				{
						// TODO: complete this!
				}
			}
		});

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
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapPersister.save(map, nameMapText.getText());
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
				
			}
		});
		
	}


	public void setMap(final Map map) {
		this.map = map;
		gridPanel.setMap(map);

	}


	private static void createAndShowGUI() {
		Map map = new Map(4, 4);
		MapEditionDialog mapEditionPanel = new MapEditionDialog(new MapJavaSerializationPersister());
		mapEditionPanel.setMap(map);
		mapEditionPanel.setVisible(true);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}


	@Override
	public void mapGridCoordinateClicked(int x, int y) {
		System.out.println("Grid was clicked on [ X: "+x+" ][ Y: "+y+ " ] ");
		//				

		if (selectedButton == SelectedButton.ENEMY_PATH) {
			map.setTile(x, y, Tile.TileType.ENEMY_PATH);
		}

		if(selectedButton == SelectedButton.SCENERY) {
			map.setTile(x, y, Tile.TileType.SCENERY);
		}
		
		if(selectedButton == SelectedButton.ENTRY) {
			
			map.setStartTile(new Coordinate(x, y));
			//map.setTile(x, y, Tile.TileType.ENEMY_PATH);
			
		}
		
		if(selectedButton == SelectedButton.EXIT) {
			map.setEndTile(new Coordinate(x, y));
			//map.setTile(x, y, Tile.TileType.ENEMY_PATH);
		}
		
	}

}
