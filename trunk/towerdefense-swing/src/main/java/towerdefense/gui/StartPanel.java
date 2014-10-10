package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;

public class StartPanel extends JPanel implements MapListPanel.MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static GridPanel gridPanel;
	static JPanel buttonPanel;
	
	
	
	private JButton startButton;
	private JButton editButton;
	private JButton exitButton;
	private JButton mapButton[];
	
	
	private static final Icon START_ICON = new ImageIcon("start.png");
	private static final Icon EDIT_ICON = new ImageIcon("edit.png");
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	
	
	
	public StartPanel(Map map) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(2,2,1,1));
		
		gridPanel = new GridPanel(map) {
			
			@Override
			public void coordinatesClicked(int x, int y) {				
				
					map.setTile(x, y, Tile.TileType.SCENERY);
				
				
			}
		};
		
		//add(gridPanel,BorderLayout.CENTER);
		setButtons();
		
	
	}
	
	
	private void setButtons() {
		
		startButton = new JButton(START_ICON );
		exitButton = new JButton(EXIT_ICON);
		editButton = new JButton(EDIT_ICON);		
		
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(startButton);		
		buttonPanel.add(editButton);
		buttonPanel.add(exitButton);
		
		mapButton = new JButton[10];
		
//	    for(int i=1;i<10;i++)
//	    {
//	    	mapButton[i]=new JButton("Map"+i);
//	    	buttonPanel.add(mapButton[i]);	    	
//	    }
		MapJavaSerializationPersister persister = new MapJavaSerializationPersister();
		MapListPanel mapListPanel = new MapListPanel(persister);
		mapListPanel.setMapSelectionListerner(this);
		add(mapListPanel);
		//add(buttonPanel,BorderLayout.CENTER);

		
	}

	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("StartPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
        Map map = new Map(10, 10);
        StartPanel startPanel = new StartPanel(map);
        frame.getContentPane().add(startPanel);
        frame.add(gridPanel,BorderLayout.CENTER);
        frame.add(buttonPanel,BorderLayout.EAST);
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
	public void mapSelected(Map map) {
		gridPanel.setMap(map);
		
	}

}
