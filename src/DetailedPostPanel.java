
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class DetailedPostPanel extends JScrollPane
{
    private Post post;
    private Color lineColor = Color.GREEN;
    private Color backgroundColor = Color.BLACK;
    private JPanel panel;
    private ServerRequest serverRequest;
    private int commentCount;
    private JScrollPane parentScrollPane;

    public DetailedPostPanel(Post post, JScrollPane parentScrollPane, ServerRequest serverRequest) throws Exception 
    {
        this.post = post;
        this.serverRequest = serverRequest;
        this.parentScrollPane = parentScrollPane;
        
        String title = post.title;
        String content = post.content;
        String series = serverRequest.getSeriesInfo(post.seriesID).name;
        String username = serverRequest.getUserInfo(post.userID).username;


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setForeground(lineColor);

        JPanel postPanel = new PostPanel(post, null, serverRequest);


        panel.add(postPanel);
        panel.add(Box.createVerticalStrut(50));
        
        this.setViewportView(panel);


        

        

        this.setBounds(0, 10, 550, 550);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = this.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Increase unit increment
        verticalScrollBar.setBlockIncrement(10); // Increase block increment

        

        JScrollPane scrollPane = this;
        this.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = (JScrollBar) e.getSource();
                int extent = scrollBar.getModel().getExtent();
                int maximum = scrollBar.getModel().getMaximum();
                int value = scrollBar.getValue() + extent;

                // Load more posts when scrollbar is near the bottom
                if (value >= maximum - 200 && scrollPane.isVisible()) 
                {
                    spawnNewComment();
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });


        SwingUtilities.invokeLater(this::initializeKeyListener);
    }

    private void initializeKeyListener() {
        // Add the KeyListener to the JScrollPane's viewport
        this.getViewport().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    closeDetailedPost();
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
    

    public void spawnNewComment()
    {
        try
        {
            List<Comment> comments = serverRequest.getComments(10, post.id, SortBy.VOTES, commentCount);
            commentCount += comments.size();
            
            

            for (Comment comment : comments)
            {
                panel.add(Box.createVerticalStrut(25));
                panel.add(new CommentPanel(comment, this, serverRequest));
            }

            if(comments.size() < 10 && !comments.isEmpty())
            {
                panel.add(Box.createVerticalStrut(25));
            }
            
            


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public void closeDetailedPost()
    {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        
        frame.remove(this);
        parentScrollPane.setVisible(true);

        parentScrollPane.revalidate();
        parentScrollPane.repaint();
       

        frame.revalidate();
        frame.repaint();
    }

}
