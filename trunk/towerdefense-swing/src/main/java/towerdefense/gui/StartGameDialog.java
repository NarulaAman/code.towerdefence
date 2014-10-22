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
import javax.swing.JPanel;

import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.io.MapPersister;
import ca.concordia.soen6441.logic.Map;

public class StartGameDialog extends JDialog implements MapListPanel.MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MapPanel gridPanel = new MapPanel();
	private JPanel sideBar;
	
	private final JButton startBtn = new JButton();
	private final JButton editBtn = new JButton();	
	private final JButton exitBtn = new JButton(EXIT_ICON);
	
	
	private final MapListPanel mapListPanel;
	
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	
	public StartGameDialog(MapPersister mapDao, MapEditAction mapEditAction, StartGamePlayAction startGamePlayAction) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		startBtn.setAction(startGamePlayAction);
		editBtn.setAction(mapEditAction);
		mapListPanel = new MapListPanel(mapDao);
		mapListPanel.addMapSelectionListerner(this);
		mapListPanel.addMapSelectionListerner(mapEditAction);
		mapListPanel.addMapSelectionListerner(startGamePlayAction);
		add(gridPanel, BorderLayout.CENTER);
		setupSideBar(mapDao);
		pack();
	}


	private void setupSideBar(MapPersister mapDao) {
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		sideBar = new JPanel();
		sideBar.setPreferredSize(new Dimension(100, 600));
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.add(startBtn);		
		sideBar.add(editBtn);
		sideBar.add(exitBtn);
		
		
		
		sideBar.add(mapListPanel);
		add(sideBar,BorderLayout.EAST);

		
	}

//	private static void createAndShowGUI() {
//        StartGameDialog startPanel = new StartGameDialog(new MapJavaSerializationPersister(), null, null);
//        
//        startPanel.setVisible(true);
//    }
// 
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }


	@Override
	public void mapSelected(Map map) {
		System.out.println("Seletec map: " + map);
		gridPanel.setMap(map);
	}

}
