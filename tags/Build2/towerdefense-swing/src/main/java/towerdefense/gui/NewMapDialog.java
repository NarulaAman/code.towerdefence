package towerdefense.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This dialog asks the user for a width and height
 *
 */
public class NewMapDialog extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 620779799725640674L;

	private NumberFormat format = NumberFormat.getNumberInstance();

	private JFormattedTextField widthField = new JFormattedTextField(format);
	private JFormattedTextField heightField = new JFormattedTextField(format);




	/**
	 * Constructs an instance of {@link NewMapDialog}
	 */
	public NewMapDialog() {
		heightField.setColumns(5);
		widthField.setColumns(5);

		widthField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != '\b')) {
					e.consume();
				}
			}
		});

		heightField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != '\b')) {
					e.consume();
				}
			}
		});

		add(new JLabel("Width:"));
		add(widthField);
		add(new JLabel("Height:"));
		add(heightField);		

	}
	/**
	 * Returns the width value on the dialog
	 * @return the width value on the dialog
	 */
	public int getWidthValue() {
		try {
			return Integer.parseInt(widthField.getText());
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * Returns the height value on the dialog
	 * @return the height value on the dialog
	 */
	public int getHeightValue() {
		try{
			return Integer.parseInt(heightField.getText());
		} catch (Exception e) {
			return 0;
		}

	}

}