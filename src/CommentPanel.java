
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

public class CommentPanel extends JPanel
{

    private final Color lineColor = Color.GREEN;
    private final Color backgroundColor = Color.BLACK;
    public Comment comment;
    public JScrollPane scrollPane;
    public ServerRequest serverRequest;

    public CommentPanel(Comment comment, JScrollPane scrollPane, ServerRequest serverRequest) throws Exception
    {
        this.comment = comment;
        this.scrollPane = scrollPane;
        this.serverRequest = serverRequest;
        
        String content = comment.content;
        User user = serverRequest.getUserInfo(comment.userID);
        

        ImageIcon icon = new ImageIcon("textures/placeHolder.jpg");
        if(!user.icon.equals(""))
        {
            try
            {
                URL url = new URI(user.icon).toURL();
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
        Image scaledImg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);


        JLabel userLabel = new JLabel(user.username, icon, JLabel.LEFT);
        userLabel.setFont(new Font("Robot", Font.PLAIN, 12));
        userLabel.setForeground(lineColor);
        userLabel.setVerticalAlignment(JLabel.TOP);
        userLabel.setBounds(50, 15, userLabel.getPreferredSize().width, 16);
        this.add(userLabel);

        
        JLabel contentLabel = new JLabel();
        contentLabel.setFont(new Font("Robot", Font.PLAIN, 16));
        contentLabel.setText("<html><div style='width: 450px;'>" + content + "</div></html>");
        contentLabel.setForeground(lineColor);
        contentLabel.setHorizontalAlignment(JLabel.LEFT);
        contentLabel.setVerticalAlignment(JLabel.TOP);
        contentLabel.setBounds(50, 40, 450, contentLabel.getPreferredSize().height);
        this.add(contentLabel);
        
       
        
        
        
        this.setLayout(new BorderLayout());
        this.setBorder(new RoundedBorder(50));
        this.setBackground(backgroundColor);
        this.setForeground(lineColor);
        int totalHeight = 40 + contentLabel.getPreferredSize().height + 20;
        this.setPreferredSize(new Dimension(500, totalHeight));
        this.setMaximumSize(new Dimension(500, totalHeight));
        
        
        this.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(scrollPane != null)
                {
                    showSubComment();
                }
                
            }
        });
        
        
    }

    


    private void showSubComment()
    {
        try
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            scrollPane.setVisible(false);
            
            frame.add(new DetailedCommentPanel(comment, scrollPane, serverRequest), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
