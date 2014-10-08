package towerdefense.gui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	DialogPanel dialogPanel = null;
	TowerPanel towerPanel = null;
	
	public MapFrame()
	{
		setVisible(true);
		int xVal;
		int yVal;
		
		dialogPanel = new DialogPanel();
		JOptionPane.showConfirmDialog(null, dialogPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		 
		 xVal =  dialogPanel.getXField();
		 yVal =  dialogPanel.getYField();
		
		while(xVal < 10 || yVal < 10 || xVal > 20 || yVal > 20)
		{
		
			JOptionPane.showMessageDialog(null, "Please enter values between 5 and 20", "Warning", JOptionPane.OK_OPTION);
		
			JOptionPane.showConfirmDialog(null, dialogPanel, 
            "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
			
			 xVal =  dialogPanel.getXField();
			 yVal =  dialogPanel.getYField();
		}
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gridPanel = new GridPanel(new Map(xVal, yVal));
		buttonPanel = new ButtonPanel();
		
		
		
		add(gridPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.EAST);
		
		//add(towerPanel,BorderLayout.SOUTH);
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
