

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;
import javax.swing.*;

public class postScrollPane extends JScrollPane
{

    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;
    public ServerRequest serverRequest;
    public JPanel postPanel;
    public JScrollPane scrollPane;

    public postScrollPane(ServerRequest serverRequest) 
    {
        this.serverRequest = serverRequest;

        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        
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
            List<Post> posts = serverRequest.getPosts(1, null, SortBy.RANDOM);
            
            postPanel.add(Box.createVerticalStrut(50));
            postPanel.add(new PostPanel(posts.get(0), scrollPane, serverRequest));
            
            


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
        
}
