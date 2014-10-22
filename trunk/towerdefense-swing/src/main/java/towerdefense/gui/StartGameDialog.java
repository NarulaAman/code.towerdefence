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
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;

/**
 * This is the Dialog that will be displayed when the user starts our game application
 *
 */
public class StartGameDialog extends JDialog implements MapListPanel.MapSelectionListener{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5034183610861487619L;
	private final MapPanel gridPanel = new MapPanel();
	private final JPanel sideBar = new JPanel();
	
	private final JButton startBtn = new JButton();
	private final JButton editBtn = new JButton();	
	private final JButton exitBtn = new JButton(EXIT_ICON);
	
	
	private final MapListPanel mapListPanel;
	
	private static final Icon EXIT_ICON = new ImageIcon(Object.class.getResource("/icons/exit.png"));
	
	/**
	 * Creates a {@link StartGameDialog} with a given {@link GameMapDao}, {@link MapEditAction}, {@link StartGamePlayAction}
	 * @param gameMapDao 
	 * @param mapEditAction
	 * @param startGamePlayAction
	 */
	public StartGameDialog(GameMapDao gameMapDao, MapEditAction mapEditAction, StartGamePlayAction startGamePlayAction) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		startBtn.setAction(startGamePlayAction);
		editBtn.setAction(mapEditAction);
		mapListPanel = new MapListPanel(gameMapDao);
		mapListPanel.addMapSelectionListerner(this);
		mapListPanel.addMapSelectionListerner(mapEditAction);
		mapListPanel.addMapSelectionListerner(startGamePlayAction);
		add(gridPanel, BorderLayout.CENTER);
		setupSideBar(gameMapDao);
		pack();
	}


	/**
	 * Setup the {@link StartGameDialog}'s side bar
	 * @param gameMapDao {@link GameMapDao} to be used by panels in the Sidebar
	 */
	private void setupSideBar(GameMapDao gameMapDao) {
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
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


	/* (non-Javadoc)
	 * @see towerdefense.gui.MapListPanel.MapSelectionListener#mapSelected(ca.concordia.soen6441.logic.GameMap)
	 */
	@Override
	public void mapSelected(GameMap gameMap) {
		gridPanel.setMap(gameMap);
	}

}
