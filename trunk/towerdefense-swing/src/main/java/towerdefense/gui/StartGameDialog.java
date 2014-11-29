package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.inject.Inject;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import towerdefense.gui.actions.DisplayLogAction;
import towerdefense.gui.actions.LoadGamePlayAction;
import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.NewMapAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GameMapDao;

/**
 * This is the Dialog that will be displayed when the user starts our game application
 *
 */
public class StartGameDialog extends JDialog implements MapListPanel.MapSelectionListener{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5034183610861487619L;
	private final MapPanel mapPanel = new MapPanel();
	private final JPanel sideBar = new JPanel();
	
	private final JButton newBtn = new JButton();
	private final JButton startBtn = new JButton();
	private final JButton editBtn = new JButton();	
	private final JButton exitBtn = new JButton(EXIT_ICON);
	private final JButton mapLogBtn = new JButton(MAP_LOG_ICON);
	private final JButton loadBtn= new JButton();
	
	
	private final MapListPanel mapListPanel;
	
	private static final Icon MAP_LOG_ICON = new ImageIcon(Object.class.getResource("/icons/maplogbutton.png"));
	private static final Icon EXIT_ICON = new ImageIcon(Object.class.getResource("/icons/exit.png"));
	/**
	 * Creates a {@link StartGameDialog} with a given {@link GameMapDao}, {@link MapEditAction}, {@link StartGamePlayAction}
	 * @param gameMapDao {@link GameMapDao} to be used
	 * @param newMapAction {@link NewMapAction} to be used
	 * @param mapEditAction {@link MapEditAction} to be used
	 * @param startGamePlayAction {@link StartGamePlayAction} to be used
	 */
	@Inject
	public StartGameDialog(GameMapDao gameMapDao, NewMapAction newMapAction, MapEditAction mapEditAction, StartGamePlayAction startGamePlayAction, DisplayLogAction displayLogAction, LoadGamePlayAction loadGamePlayAction) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Chicken Tikka Masala Gang - Tower Defense");
		setLayout(new BorderLayout());
		newBtn.setAction(newMapAction);
		startBtn.setAction(startGamePlayAction);
		editBtn.setAction(mapEditAction);
		mapLogBtn.setAction(displayLogAction);
		loadBtn.setAction(loadGamePlayAction);
		mapListPanel = new MapListPanel(gameMapDao);
		mapListPanel.addMapSelectionListerner(this);
		mapListPanel.addMapSelectionListerner(mapEditAction);
		mapListPanel.addMapSelectionListerner(startGamePlayAction);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		add(mapPanel, BorderLayout.CENTER);
		setupSideBar(gameMapDao);
		setResizable(false);
		setVisible(true);
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
		sideBar.add(newBtn);
		sideBar.add(startBtn);		
		sideBar.add(editBtn);
		sideBar.add(exitBtn);
		sideBar.add(mapLogBtn);
		sideBar.add(loadBtn);
		sideBar.add(mapListPanel);
		add(sideBar,BorderLayout.EAST);

		
	}

	/**
	 * This method called when a certain map is being selected
	 * @param gameMap that being selected
	 */
	@Override
	public void mapSelected(GameMap gameMap) {
		mapPanel.setMap(gameMap);
	}
	
	/**
	 * This method called when a map is saved on MapEditionPanel
	 * @param gameMap that being saved
	 */
	public void updateGameMap(GameMap gameMap) {
		mapListPanel.removeSelection();
		mapPanel.setMap(gameMap);
	}
	
	/**
	 * Method to be called when this window is disposed 
	 */
	@Override
	public void dispose() {
		mapPanel.dispose();
	}

	/**
	 * Refreshes the map list
	 */
	public void refreshMaps() {
		mapListPanel.refreshList();
	}
	
//	private static void createAndShowGUI() {
//  StartGameDialog startPanel = new StartGameDialog(new MapJavaSerializationPersister(), null, null);
//  
//  startPanel.setVisible(true);
//}
//
//public static void main(String[] args) {
//  //Schedule a job for the event-dispatching thread:
//  //creating and showing this application's GUI.
//  javax.swing.SwingUtilities.invokeLater(new Runnable() {
//      public void run() {
//          createAndShowGUI();
//      }
//  });
//}
}
