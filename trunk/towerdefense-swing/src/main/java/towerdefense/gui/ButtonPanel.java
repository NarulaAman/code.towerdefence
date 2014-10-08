package towerdefense.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
	
	private static final Icon NEW_ICON = new ImageIcon("newb.png");
	private static final Icon ENTRY_ICON = new ImageIcon("entry.png");
	private static final Icon EXIT_ICON = new ImageIcon("exit.png");
	private static final Icon PATH_ICON = new ImageIcon("path.png");
	private static final Icon SCENERY_BUTTON_ICON = new ImageIcon("scenery.png");
	private static final Icon SAVE_ICON = new ImageIcon("save.png");
	
	
	public static boolean path = false;
	public static boolean scenery = false;

	public ButtonPanel()
	{
		setVisible(true);
		setBackground(Color.gray);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		new_button = new JButton(NEW_ICON);
		entry_button = new JButton(ENTRY_ICON );
		exit_button = new JButton(EXIT_ICON);
		path_button = new JButton(PATH_ICON);
		scenery_button = new JButton(SCENERY_BUTTON_ICON);
		save_button = new JButton(SAVE_ICON);
		
		
		new_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Game?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION);
				{
					
				}
			}
		});
		
		exit_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
		
		path_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				path = true;
				scenery = false;
			}
		});
		
		scenery_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				scenery = true;	
				path = false;
			}
		});

		add(new_button);
		add(entry_button);
		add(exit_button);
		add(path_button);
		add(scenery_button);		
		add(save_button);




	}



}
