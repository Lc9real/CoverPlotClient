
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchbarPanel extends JPanel
{

    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;


    public SearchbarPanel() 
    {
        // TODO : Search bar
        JTextField textField = new JTextField(32);
        textField.setBackground(backgroundColor);
        textField.setForeground(lineColor);
        textField.setBorder(new RoundedBorder(20));

        textField.setPreferredSize(new Dimension(300, 30));

        this.add(textField);
    }
    
}
