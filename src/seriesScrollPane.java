
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class seriesScrollPane extends JScrollPane
{
    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;
    public ServerRequest serverRequest;
    public JPanel postPanel;
    public JScrollPane scrollPane;
    private JScrollPane parentScrollPane;
    public Series series;

    public seriesScrollPane(Series series, JScrollPane parentScrollPane, ServerRequest serverRequest) 
    {
        this.series = series;
        this.serverRequest = serverRequest;
        this.parentScrollPane = parentScrollPane;








        JPanel seriesInfoPanel = new JPanel();



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
        Image scaledImg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        seriesIcon = new ImageIcon(scaledImg);



        JLabel seriesLabel = new JLabel(series.name, seriesIcon, JLabel.LEFT);
        seriesLabel.setFont(new Font("Robot", Font.PLAIN, 24));
        
        seriesLabel.setForeground(lineColor);
        seriesLabel.setHorizontalAlignment(JLabel.LEFT);
        seriesLabel.setVerticalAlignment(JLabel.TOP);
        seriesLabel.setBounds(25, 15, seriesLabel.getPreferredSize().width, 50);
        seriesInfoPanel.add(seriesLabel);

        

    
        



        
        
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Robot", Font.PLAIN, 16));
        descriptionLabel.setText("<html><div style='width: 450px;'>" + series.description + "</div></html>");
        descriptionLabel.setForeground(lineColor);
        descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
        descriptionLabel.setVerticalAlignment(JLabel.TOP);
        descriptionLabel.setBounds(20, 100, 450, descriptionLabel.getPreferredSize().height);
        seriesInfoPanel.add(descriptionLabel);
        
       
        
        
        
        seriesInfoPanel.setLayout(new BorderLayout());
        seriesInfoPanel.setBorder(new RoundedBorder(50));
        seriesInfoPanel.setBackground(backgroundColor);
        seriesInfoPanel.setForeground(lineColor);
        int totalHeight = 100 + descriptionLabel.getPreferredSize().height + 80;
        seriesInfoPanel.setPreferredSize(new Dimension(600, totalHeight));
        seriesInfoPanel.setMaximumSize(new Dimension(600, totalHeight));

        













        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.add(seriesInfoPanel);
        postPanel.setBackground(backgroundColor);

        this.setViewportView(postPanel);

        scrollPane = this;

        spawnInitialPosts();



        this.setBounds(0, 10, 550, 550);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = this.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(40); // Increase unit increment
        verticalScrollBar.setBlockIncrement(5); // Increase block increment

     

        this.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = (JScrollBar) e.getSource();
                int extent = scrollBar.getModel().getExtent();
                int maximum = scrollBar.getModel().getMaximum();
                int value = scrollBar.getValue() + extent;

                // Load more posts when scrollbar is near the bottom
                if (value >= maximum - 200 && scrollPane.isVisible()) {
                    spawnNewPost();
                    postPanel.revalidate();
                    postPanel.repaint();
                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            }
        });

        SwingUtilities.invokeLater(this::initializeKeyListener);
    }

    public void spawnInitialPosts()
    {
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
    }

    public void spawnNewPost()
    {
        try
        {
            List<Post> posts = serverRequest.getPosts(1, series.id, SortBy.RANDOM);
            
            postPanel.add(Box.createVerticalStrut(50));
            postPanel.add(new PostPanel(posts.get(0), scrollPane, serverRequest));
            
            


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    private void initializeKeyListener() {
        // Add the KeyListener to the JScrollPane's viewport
        this.getViewport().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    closeSeriesView();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });

        this.getViewport().setFocusable(true);
        this.getViewport().requestFocusInWindow();
    }

    public void closeSeriesView()
    {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        
        frame.remove(this);
        parentScrollPane.setVisible(true);
        frame.add(parentScrollPane);
        parentScrollPane.revalidate();
        parentScrollPane.repaint();
       

        frame.revalidate();
        frame.repaint();
    }
        
}
