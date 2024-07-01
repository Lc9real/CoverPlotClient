
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SeriesPanel extends JPanel
{

    private Series series;

    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;

    public SeriesPanel(Series series) 
    {
        this.series = series;

        ImageIcon icon = new ImageIcon("textures/placeHolder.jpg");
        if(!series.icon.equals(""))
        {
            try
            {
                URL url = new URI(series.icon).toURL();
                BufferedImage image = ImageIO.read(url);
                icon = new ImageIcon(image);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
        
        // Scale the icon to a smaller size if needed
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);

        

        JLabel seriesLabel = new JLabel(series.name, icon, JLabel.LEFT);
        seriesLabel.setFont(new Font("Robot", Font.PLAIN, 16));
        seriesLabel.setForeground(lineColor);
        seriesLabel.setHorizontalAlignment(JLabel.LEFT);
        seriesLabel.setVerticalAlignment(JLabel.TOP);
        seriesLabel.setBounds(50, 15, 150, 32);
       


        this.setLayout(new BorderLayout());
        this.add(seriesLabel);
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        this.setBackground(backgroundColor);
        this.setForeground(lineColor);
        this.setPreferredSize(new Dimension(200, 32));
        this.setMaximumSize(new Dimension(200, 32));
    }


    
    
}
