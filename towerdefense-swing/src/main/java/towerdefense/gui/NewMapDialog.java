package towerdefense.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewMapDialog extends JPanel{
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

    private JTextField xField = null;
    private JTextField yField = null;
        public NewMapDialog()
        {
              xField = new JTextField(5);
              yField = new JTextField(5);
              
              add(new JLabel("x:"));
              add(xField);
             // add(Box.createHorizontalStrut(15)); // a spacer
              add(new JLabel("y:"));
              add(yField);
        }
        
        public int getXField()
        {
                return Integer.parseInt(xField.getText());
                
        }
        
        public int getYField()
        {
                return Integer.parseInt(yField.getText());
                
        }

}