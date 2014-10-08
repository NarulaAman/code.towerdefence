package towerdefense.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TowerPanel extends JPanel implements MouseMotionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] towers;
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;

	public TowerPanel()
	{
		setVisible(true);
		GridLayout grid = new GridLayout(3, 3,1,1);
		setLayout(grid);
		towers = new JButton[3][3];
		for(int t=0;t<3;t++)
		{
			for(int k=0;k<3;k++)
			{
				towers[t][k]= new JButton();
				towers[t][k].setSize(15, 15);
				towers[t][k].setBackground(Color.black);
				towers[t][k].addMouseMotionListener(new MouseMotionListener() {
					
					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						int deltaX = e.getXOnScreen() - screenX;
						int deltaY = e.getYOnScreen() - screenY;

						setLocation(myX + deltaX, myY + deltaY);

						
					}
				});
                
				add(towers[t][k]);
			}
		}




	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		


	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		screenX = e.getXOnScreen();
		screenY = e.getYOnScreen();

		myX = getX();
		myY = getY();

	}



	@Override
	public void mouseReleased(MouseEvent e) {
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



}
