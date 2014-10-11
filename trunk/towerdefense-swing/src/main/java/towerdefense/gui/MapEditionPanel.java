package towerdefense.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.io.MapPersister;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class MapEditionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MapPanel gridPanel;
    Map map;

	enum SelectedButton {
		SCENERY,
		ENEMY_PATH,
		ENTRY,
		EXIT
	}


	private JButton newButton = new JButton(NEW_ICON);;
	private JButton entry_button = new JButton(ENTRY_ICON);
	private JButton exit_button= new JButton(EXIT_ICON);
	private JButton path_button = new JButton(PATH_ICON);
	private JButton scenery_button = new JButton(SCENERY_BUTTON_ICON);
	private JButton save_button = new JButton(SAVE_ICON);

	private static final Icon NEW_ICON = new ImageIcon("newb.png");
	private static final Icon ENTRY_ICON = new ImageIcon("entry.png");
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	private static final Icon PATH_ICON = new ImageIcon("path.png");
	private static final Icon SCENERY_BUTTON_ICON = new ImageIcon("scenery.png");
	private static final Icon SAVE_ICON = new ImageIcon("save.png");

	private final MapPersister mapPersister;
	SelectedButton selectedButton = SelectedButton.SCENERY;

	public MapEditionPanel(Map map, MapPersister persister) {
		this.map = map;
		setLayout(new GridLayout(2,2,1,1));

		this.mapPersister = persister;
		gridPanel = new MapPanel(map) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void coordinatesClicked(int x, int y) {
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
					map.setTile(x, y, Tile.TileType.ENEMY_PATH);
					
				}
				
				if(selectedButton == SelectedButton.EXIT) {
					map.setEndTile(new Coordinate(x, y));
					map.setTile(x, y, Tile.TileType.ENEMY_PATH);
				}

			}
		};

		add(gridPanel);
		setuButtons();


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

		exit_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});

		path_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.ENEMY_PATH;
			}
		});

		scenery_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.SCENERY;
			}
		});
		
		entry_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedButton = SelectedButton.ENTRY;
				
				
			}
		});
		
		save_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapPersister.save(map, "SomeMap");
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
				
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(newButton);
		buttonPanel.add(entry_button);
		buttonPanel.add(exit_button);
		buttonPanel.add(path_button);
		buttonPanel.add(scenery_button);		
		buttonPanel.add(save_button);

		add(buttonPanel);


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
		MapEditionPanel mapEditionPanel = new MapEditionPanel(map, new MapJavaSerializationPersister());
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

}
