
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.net.Socket;
import java.util.List;
import javax.swing.*;



public class UserWindow 
{
    public static String ipAdress = "localhost";
    public static int port = 9806;
    public static Socket socket;
    public static ServerRequest serverRequest;
    public static JPanel postPanel;

    public static void main(String[] args) throws Exception
    {   
        System.out.println("Starting Client...");
        socket = new Socket(ipAdress, port);
        System.out.println("Connected to Server :)");
        serverRequest = new ServerRequest(socket);
        

        JPanel seriesPanel = new JPanel();
        seriesPanel.setBackground(Color.red);
        seriesPanel.setPreferredSize(new Dimension(200, 300));

        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        
        postPanel.setBackground(Color.BLACK);

        spawnInitialPosts();
        
        

        JScrollPane scrollPane = new JScrollPane(postPanel);
        scrollPane.setBounds(0, 10, 550, 550);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Increase unit increment
        verticalScrollBar.setBlockIncrement(10); // Increase block increment

        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = (JScrollBar) e.getSource();
                int extent = scrollBar.getModel().getExtent();
                int maximum = scrollBar.getModel().getMaximum();
                int value = scrollBar.getValue() + extent;

                // Load more posts when scrollbar is near the bottom
                if (value >= maximum - 200) {
                    spawnNewPost();
                    postPanel.revalidate();
                    postPanel.repaint();
                }
            }
        });


        


        JFrame frame = new JFrame();
        frame.setTitle("CoverPlot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("textures/profilepicture.jpg");
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setSize(750, 750);
        frame.setVisible(true);


        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(seriesPanel, BorderLayout.WEST);
       

        
        
        
    }

    public static void spawnInitialPosts()
    {
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
        spawnNewPost();
    }

    public static void spawnNewPost()
    {
        try
        {
            List<Post> posts = serverRequest.getPosts(1, null, SortBy.RANDOM);
            String title = posts.get(0).title;
            String content = posts.get(0).content;
            String series = serverRequest.getSeriesInfo(posts.get(0).seriesID).name;
            String username = serverRequest.getUserInfo(posts.get(0).userID).username;
            postPanel.add(Box.createVerticalStrut(50));
            postPanel.add(new PostPanel(title, content, series, "", username, ""));


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    
}
