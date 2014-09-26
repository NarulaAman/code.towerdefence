package towerdefense.gui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ca.concordia.soen6441.logic.Map;




public class MapFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel gridPanel = null;
	JPanel buttonPanel = null;
	
	public MapFrame()
	{
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gridPanel = new GridPanel(new Map(10, 10));
		buttonPanel = new ButtonPanel();
		add(gridPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.EAST);
		pack();
	}

	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new MapFrame();
	            }
	        });

	    }
}
