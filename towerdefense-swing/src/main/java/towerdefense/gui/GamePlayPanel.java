package towerdefense.gui;

import java.awt.BorderLayout;
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

import towerdefense.gui.MapEditionPanel.SelectedButton;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;

public class GamePlayPanel extends JPanel{
	
	static GridPanel gridPanel;
	static JPanel panelLabel;
	static JPanel buttonPanel; 
	
	
	private JLabel Label1;
	private JLabel Label2;
	private JLabel Label3;
	private JLabel Label4;
	
	private JButton Tower1Button;
	private JButton Tower2Button;
	private JButton Tower3Button;
	private JButton Tower4Button;
	private JButton Tower5Button;
	
	public GamePlayPanel(Map map) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(2,2,1,1));
		
		gridPanel = new GridPanel(map) {
			
			@Override
			public void coordinatesClicked(int x, int y) {
				map.setTile(x, y, Tile.TileType.SCENERY);
			}
		};
		
		//add(gridPanel);
		setuButtons();
		
	
	}
	
private void setuButtons() {
	
	Label1 = new JLabel("Lives");
	Label2 = new JLabel("Scores");
	Label3 = new JLabel("Levels");
	Label4 = new JLabel("Banks");
	
	Tower1Button = new JButton("Tower1");
	Tower2Button = new JButton("Tower2");
	Tower3Button = new JButton("Tower3");
	Tower4Button = new JButton("Tower4");
	Tower5Button = new JButton("Tower5");
		
		
	Tower1Button.addMouseListener(new MouseListener() {
			
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			 JOptionPane.showConfirmDialog (null, "Would You Like to Save your Game?","Warning",JOptionPane.YES_NO_OPTION);
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		});
	    
	    panelLabel=new JPanel();
	    panelLabel.setLayout(new GridLayout(1,4));
	    
	    JLabel Label1 = new JLabel("Lives");	
        JLabel Label2 = new JLabel("Scores");
        JLabel Label3 = new JLabel("Level");
        JLabel Label4 = new JLabel("Banks");
        
        panelLabel.add(Label1);
        panelLabel.add(Label2);
        panelLabel.add(Label3);
        panelLabel.add(Label4);
        	
		buttonPanel = new JPanel();
//		buttonPanel.setLayout(new GridLayout(2,3));
		buttonPanel.add(Tower1Button);
		buttonPanel.add(Tower2Button);
		buttonPanel.add(Tower3Button);
		buttonPanel.add(Tower4Button);
		buttonPanel.add(Tower5Button);		
		
		
		
}	

	

		private static void createAndShowGUI() {

        JFrame frame = new JFrame("GamePlayPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Map map = new Map(10,10);
        GamePlayPanel GamePlayPanel = new GamePlayPanel(map);
        frame.getContentPane().add(GamePlayPanel);
        frame.add(gridPanel,BorderLayout.CENTER);
        frame.add(panelLabel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.EAST);
 
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
}
