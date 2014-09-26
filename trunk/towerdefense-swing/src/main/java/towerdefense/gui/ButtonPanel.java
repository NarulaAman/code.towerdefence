package towerdefense.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton new_button;
	private JButton entry_button;
	private JButton exit_button;
	private JButton path_button;
	private JButton scenery_button;
	private JButton save_button;
	
	
	public ButtonPanel()
	{
		setVisible(true);
		setBackground(Color.black);
		setLayout(new GridLayout(6,1,4,4));
		new_button = new JButton("New");
		entry_button = new JButton("Entry");
		exit_button = new JButton("Exit");
		path_button = new JButton("Path");
		scenery_button = new JButton("Scenery");
		save_button = new JButton("Save");
		
		
		entry_button.setBackground(new Color(215, 19, 19));
		
		add(new_button);
		add(entry_button);
		add(exit_button);
		add(path_button);
		add(scenery_button);		
		add(save_button);
		
		
	}
	
	

}
