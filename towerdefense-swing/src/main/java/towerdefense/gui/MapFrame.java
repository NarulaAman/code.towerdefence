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
	private JPanel gridPanel = null;
	private JPanel buttonPanel = null;
	private DialogPanel dialogPanel = null;
	private Map map = null;

	private final int xyMin = 10;
	private final int xyMax = 20;


	public MapFrame()
	{
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int xVal;
		int yVal;

		dialogPanel = new DialogPanel();
		JOptionPane.showConfirmDialog(null, dialogPanel, 
				"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

		xVal =  dialogPanel.getXField();
		yVal =  dialogPanel.getYField();

		while(xVal < xyMin || yVal < xyMin || xVal > xyMax || yVal > xyMax) {

			JOptionPane.showMessageDialog(null, "Please enter values between 10 and 20", "Warning", JOptionPane.OK_OPTION);

			JOptionPane.showConfirmDialog(null, dialogPanel, 
					"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

			xVal =  dialogPanel.getXField();
			yVal =  dialogPanel.getYField();
		}


		map = new Map(xVal, yVal);
		gridPanel = new GridPanel(map);
		buttonPanel = new ButtonPanel();


		add(gridPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.EAST);

		pack();
		
	}

    public Map getMap()
    {
    	return map;
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
