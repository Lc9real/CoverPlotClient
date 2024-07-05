
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchbarPanel extends JPanel 
{
    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;

    public SearchbarPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        // Create the search text field
        JTextField searchField = new JTextField("Enter text...", 30);
        searchField.setLayout(new BorderLayout());
        JLabel label = new JLabel("icon");
        label.setCursor(Cursor.getDefaultCursor());
        searchField.add(label, BorderLayout.LINE_END);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchField.setText("");
            }
        });

       this.add(searchField);
    }

   
    
}
