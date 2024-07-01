
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class DetailedCommentPanel extends JScrollPane
{

    private Comment comment;
    private final Color lineColor = Color.GREEN;
    private final Color backgroundColor = Color.BLACK;
    private JPanel panel;
    private ServerRequest serverRequest;
    private JScrollPane parentScrollPane;

    public DetailedCommentPanel(Comment comment, JScrollPane parentScrollPane, ServerRequest serverRequest) throws Exception 
    {
        this.comment = comment;
        this.serverRequest = serverRequest;
        this.parentScrollPane = parentScrollPane;
        
        String content = comment.content;
        String username = serverRequest.getUserInfo(comment.userID).username;


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setForeground(lineColor);

        JPanel commentPanel = new CommentPanel(comment, null, serverRequest);


        panel.add(commentPanel);
        panel.add(Box.createVerticalStrut(50));
        
        this.setViewportView(panel);


        

        

        this.setBounds(0, 10, 550, 550);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = this.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Increase unit increment
        verticalScrollBar.setBlockIncrement(10); // Increase block increment

        spawnSubComments();


        SwingUtilities.invokeLater(this::initializeKeyListener);
    }


    private void spawnSubComments()
    {
        try
        {
            List<Comment.CommentLevel> comments = serverRequest.getAllSubComments(List.of(comment), 0);
            // Removes the parent Comment
            comments.remove(0);
            for(Comment.CommentLevel commentLevel : comments)
            {

                // TODO : Fix Comment Offset

                JPanel commentPanel = new CommentPanel(commentLevel.comment, null, serverRequest);
                
                
                panel.add(commentPanel);

                
            }
        }
        catch(Exception e) {e.printStackTrace();}
    }

    private void initializeKeyListener() {
        // Add the KeyListener to the JScrollPane's viewport
        this.getViewport().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    closeSubComments();
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


    public void closeSubComments()
    {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        parentScrollPane.setVisible(true);
        frame.remove(this);
        parentScrollPane.revalidate();
        parentScrollPane.repaint();
        frame.revalidate();
        frame.repaint();
    }

}
