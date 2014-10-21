package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class MapEditionPanel extends JPanel implements MapGridCoordinateClickedListener {

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


	private JButton newButton = new JButton(NEW_ICON);;
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

	public MapEditionPanel(MapPersister persister) {
		setLayout(new BorderLayout());
		
		this.mapPersister = persister;
		

		add(gridPanel,BorderLayout.CENTER);
		setuButtons();
		
		JPanel nameMapPanel = new JPanel(new GridLayout(1,2,2,2));
		JLabel nameMapLabel = new JLabel("Please Enter the Map Name");
		
		nameMapPanel.add(nameMapLabel);
		nameMapPanel.add(nameMapText);
		add(nameMapPanel,BorderLayout.SOUTH);


	}


	private void setuButtons() {

		
		


		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Game?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION);
				{

				}
			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(newButton);
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


	public void setMap(final Map map) {
//TODO : Update the map for the game play panel

	}


	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Tower Defense :: APP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add the ubiquitous "Hello World" label.
		Map map = new Map(4, 4);
		MapEditionPanel mapEditionPanel = new MapEditionPanel(new MapJavaSerializationPersister());
		frame.getContentPane().add(mapEditionPanel);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
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
