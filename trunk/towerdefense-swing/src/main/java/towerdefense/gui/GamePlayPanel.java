package towerdefense.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextField;

import towerdefense.gui.MapEditionPanel.SelectedButton;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;

public class GamePlayPanel extends JPanel implements MouseListener{

	static GridPanel gridPanel;
	static JPanel panelLabel;
	static JPanel buttonPanel; 


	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JTextField TextField1;
	private JTextField TextField2;

	private JTextField TextField3;
	private JTextField TextField4;

	private JButton tower1Button;
	private JButton tower2Button;

	JPanel inspectionWindow;
	static GamePlayPanel GamePlayPanel;

	public GamePlayPanel(Map map) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(2,2,1,1));

		gridPanel = new GridPanel(map) {

			@Override
			public void coordinatesClicked(int x, int y) {
				// do nothing, really
				//				map.setTile(x, y, Tile.TileType.SCENERY);
			}
		};

		add(gridPanel, BorderLayout.CENTER);
		setuButtons();


	}

	private void setuButtons() {

        inspectionWindow = new JPanel(new GridLayout(1,2));
		//inspectionWindow = new JPanel(new GridBagLayout());
		
		JPanel labelContainer = new JPanel(new GridLayout(4,1));
		
		//JPanel labelContainer = new JPanel();
		label1 = new JLabel("Lives");
		label2 = new JLabel("Scores");
		label3 = new JLabel("Levels");
		label4 = new JLabel("Banks");
	
		labelContainer.add(label1);
		labelContainer.add(label2);
		labelContainer.add(label3);
		labelContainer.add(label4);
		
		inspectionWindow.add(labelContainer);
		TextField1 = new JTextField("8");
		TextField2 = new JTextField("540");
		TextField3 = new JTextField("2");
		TextField4 = new JTextField("34");
		
		this.add(inspectionWindow);
		
		tower1Button = new JButton("Tower1");
		tower2Button = new JButton("BUY");

		buttonPanel = new JPanel();
		buttonPanel.add(tower1Button);		

		//		add(panelLabel,BorderLayout.EAST);
		add(buttonPanel);	
		tower1Button.addMouseListener(this);
		
	}	

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("GamePlayPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Map map = new Map(10,10);
		GamePlayPanel = new GamePlayPanel(map);
		frame.setContentPane(GamePlayPanel);

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
		
		textContainer.add(TextField1);
	    
		textContainer.add(TextField2);
	
		textContainer.add(TextField3);
		
		textContainer.add(TextField4);

 //       textContainer.setVisible(true);
		inspectionWindow.add(textContainer);
		revalidate();

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
