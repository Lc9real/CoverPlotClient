
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class PostPanel extends JPanel
{
    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;

    PostPanel(String title, String content, String series, String seriesIcon, String username, String userIcon)
    {
        
        

        JLabel seriesLabel = new JLabel();
        seriesLabel.setFont(new Font("Robot", Font.PLAIN, 12));
        seriesLabel.setText(series);
        seriesLabel.setForeground(lineColor);
        seriesLabel.setHorizontalAlignment(JLabel.LEFT);
        seriesLabel.setVerticalAlignment(JLabel.TOP);
        seriesLabel.setBounds(50, 15, seriesLabel.getPreferredSize().width, 16);
        this.add(seriesLabel);

        JLabel userLabel = new JLabel();
        userLabel.setFont(new Font("Robot", Font.PLAIN, 12));
        userLabel.setText(username);
        userLabel.setForeground(lineColor);
        userLabel.setHorizontalAlignment(JLabel.RIGHT);
        userLabel.setVerticalAlignment(JLabel.TOP);
        userLabel.setBounds(425, 15, userLabel.getPreferredSize().width, 16);
        this.add(userLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Robot", Font.PLAIN, 32));
        titleLabel.setText(title);
        titleLabel.setForeground(lineColor);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setBounds(20, 50, titleLabel.getPreferredSize().width, 38);
        this.add(titleLabel);
        
        JLabel contentLabel = new JLabel();
        contentLabel.setFont(new Font("Robot", Font.PLAIN, 16));
        contentLabel.setText("<html><div style='width: 450px;'>" + content + "</div></html>");
        contentLabel.setForeground(lineColor);
        contentLabel.setHorizontalAlignment(JLabel.LEFT);
        contentLabel.setVerticalAlignment(JLabel.TOP);
        contentLabel.setBounds(20, 100, 450, contentLabel.getPreferredSize().height);
        this.add(contentLabel);
        
       
        
        
        
        this.setLayout(new BorderLayout());
        this.setBorder(new RoundedBorder(50));
        this.setBackground(backgroundColor);
        this.setForeground(lineColor);
        int totalHeight = 100 + contentLabel.getPreferredSize().height + 20;
        this.setPreferredSize(new Dimension(500, totalHeight));
        this.setMaximumSize(new Dimension(500, totalHeight));
        
        

        
        
    }
}
