package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class GamePlayPanel extends JPanel implements MouseListener{

	MapPanel gridPanel;
	JPanel panelLabel;
	JPanel buttonPanel; 


	private JLabel livesLbl = new JLabel("Lives");
	private JLabel scoreLbl = new JLabel("Scores");
	private JLabel levelsLbl = new JLabel("Levels");
	private JLabel banksLbl = new JLabel("Banks");

	private JTextField livesTxtFld = new JTextField("8");
	private JTextField scoreTxtFld = new JTextField("540");
	private JTextField levelsTxtFld = new JTextField("2");
	private JTextField banksTxtFld = new JTextField("34");
	
	private TowerPanel towerInspectionPanel = new TowerPanel();
	
	private JButton buyTower1Btn = new JButton("Tower1");
	private JButton tower2Button = new JButton("BUY");

	JPanel inspectionWindow;

	public GamePlayPanel(Map map) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());

		gridPanel = new MapPanel(map) {

			@Override
			public void coordinatesClicked(int x, int y) {
				// do nothing, really
				//				map.setTile(x, y, Tile.TileType.SCENERY);
			}
		};

		add(gridPanel, BorderLayout.CENTER);
		setupSidebar();


	}

	private void setupSidebar() {
		
		
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
		setupGamePlayAttributes(sideBar);
		
		setupTowerAvailableToBuyPanel(sideBar);

		setupInspectionWindow(sideBar);
        inspectionWindow = new JPanel(new GridLayout(1,2));
		//inspectionWindow = new JPanel(new GridBagLayout());
	
		

		

		
		
		
//		this.add(inspectionWindow);
	

		
		
		//		add(panelLabel,BorderLayout.EAST);
//		add(buttonPanel);	
		buyTower1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		add(sideBar, BorderLayout.EAST);
	}	

	private void setupInspectionWindow(JPanel sideBar) {
		sideBar.add(towerInspectionPanel);
		towerInspectionPanel.show(new TowerFactory().towerOnCoordinate(Tower.class, new Coordinate(0,0)));
	}

	private void setupTowerAvailableToBuyPanel(JPanel sideBar) {
		JPanel towersToBuyPanel = new JPanel();
		towersToBuyPanel.add(buyTower1Btn);
		buyTower1Btn.setToolTipText("<html>Range: 4<br>Damage: 10<br>Refund rate: 90</html>" );
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

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("GamePlayPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Map map = new Map(10,10);
		GamePlayPanel gamePlayPanel = new GamePlayPanel(map);
		frame.setContentPane(gamePlayPanel);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		

//		JPanel textContainer = new JPanel();
		JPanel textContainer = new JPanel(new GridLayout(4,1));
		
		textContainer.add(livesTxtFld);
	    
		textContainer.add(scoreTxtFld);
	
		textContainer.add(levelsTxtFld);
		
		textContainer.add(banksTxtFld);

 //       textContainer.setVisible(true);
		inspectionWindow.add(textContainer);
		revalidate();

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
