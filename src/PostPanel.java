
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PostPanel extends JPanel
{
    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;
    public Post post;
    public JScrollPane scrollPane;
    public ServerRequest serverRequest;

    PostPanel(Post post, JScrollPane scrollPane, ServerRequest serverRequest) throws Exception
    {
        this.post = post;
        this.scrollPane = scrollPane;
        this.serverRequest = serverRequest;
        
        String title = post.title;
        String content = post.content;
        Series series = serverRequest.getSeriesInfo(post.seriesID);
        User user = serverRequest.getUserInfo(post.userID);


        ImageIcon seriesIcon = new ImageIcon("textures/placeHolder.jpg");
        if(!series.icon.equals(""))
        {
            try
            {
                URL url = new URI(series.icon).toURL();
                BufferedImage image = ImageIO.read(url);
                seriesIcon = new ImageIcon(image);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
        
        // Scale the icon to a smaller size if needed
        Image img = seriesIcon.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        seriesIcon = new ImageIcon(scaledImg);



        JLabel seriesLabel = new JLabel(series.name, seriesIcon, JLabel.LEFT);
        seriesLabel.setFont(new Font("Robot", Font.PLAIN, 12));
        
        seriesLabel.setForeground(lineColor);
        seriesLabel.setHorizontalAlignment(JLabel.LEFT);
        seriesLabel.setVerticalAlignment(JLabel.TOP);
        seriesLabel.setBounds(25, 15, seriesLabel.getPreferredSize().width, 25);
        this.add(seriesLabel);

        ImageIcon userIcon = new ImageIcon("textures/placeHolder.jpg");
        if(!user.icon.equals(""))
        {
            try
            {
                URL url = new URI(series.icon).toURL();
                BufferedImage image = ImageIO.read(url);
                userIcon = new ImageIcon(image);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
        
        // Scale the icon to a smaller size if needed
        img = userIcon.getImage();
        scaledImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(scaledImg);

    
        JLabel userLabel = new JLabel(user.username, userIcon, JLabel.RIGHT);
        userLabel.setFont(new Font("Robot", Font.PLAIN, 12));
        userLabel.setForeground(lineColor);
        userLabel.setHorizontalAlignment(JLabel.RIGHT);
        userLabel.setVerticalAlignment(JLabel.TOP);
        userLabel.setHorizontalTextPosition(JLabel.LEFT);
        userLabel.setVerticalTextPosition(JLabel.CENTER);
        userLabel.setBounds(425, 15, userLabel.getPreferredSize().width, 25);
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
        
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                if(scrollPane != null)
                {
                    showDetailedView();
                }
                
            }
        });
        
        
    }


    private void showDetailedView()
    {
        try
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            scrollPane.setVisible(false);
            
            frame.add(new DetailedPostPanel(post, scrollPane, serverRequest), BorderLayout.CENTER);
            
            frame.revalidate();
            frame.repaint();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

        
    }
}
