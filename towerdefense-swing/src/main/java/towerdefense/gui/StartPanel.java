package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.logic.Map;

public class StartPanel extends JDialog implements MapListPanel.MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MapPanel gridPanel = new MapPanel();
	private JPanel sideBar;
	
	private JButton startButton;
	private JButton editButton;
	private JButton exitButton;
	
	private static final Icon START_ICON = new ImageIcon("start.png");
	private static final Icon EDIT_ICON = new ImageIcon("edit.png");
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	
	public StartPanel() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setButtons();

		add(gridPanel, BorderLayout.CENTER);
	
	}


	private void setButtons() {
		
		startButton = new JButton(START_ICON );
		exitButton = new JButton(EXIT_ICON);
		editButton = new JButton(EDIT_ICON);		
		
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		sideBar = new JPanel();
		sideBar.setPreferredSize(new Dimension(100, 600));
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.add(startButton);		
		sideBar.add(editButton);
		sideBar.add(exitButton);
		
		MapJavaSerializationPersister persister = new MapJavaSerializationPersister();
		
		MapListPanel mapListPanel = new MapListPanel(persister);
		mapListPanel.addMapSelectionListerner(this);
		sideBar.add(mapListPanel);
		add(sideBar,BorderLayout.EAST);

		
	}

	
	private static void createAndShowGUI() {
        //Create and set up the window.
//        JFrame frame = new JFrame("StartPanel");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Map map = new Map(10, 10);
        StartPanel startPanel = new StartPanel();
        
//        frame.setContentPane(startPanel);
        //Display the window.
        startPanel.pack();
        startPanel.setVisible(true);
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
		System.out.println("Seletec map: " + map);
		gridPanel.setMap(map);
	}

}
