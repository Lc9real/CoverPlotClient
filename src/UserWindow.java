
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
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
    public static JScrollPane scrollPane;
    private static Color lineColor = Color.GREEN;
    private static Color backgroundColor = Color.BLACK;

    public static void main(String[] args) throws Exception
    {   
        
        System.out.println("Starting Client...");
        socket = new Socket(ipAdress, port);
        System.out.println("Connected to Server :)");
        serverRequest = new ServerRequest(socket);
        
        // TODO : Create the Series Panel
        

        JPanel seriesMenuPanel = new JPanel();
        seriesMenuPanel.setBackground(backgroundColor);
        seriesMenuPanel.setForeground(lineColor);
        seriesMenuPanel.setPreferredSize(new Dimension(200, 300));
        seriesMenuPanel.setLayout(new BoxLayout(seriesMenuPanel, BoxLayout.Y_AXIS));

        List<Series> joinedSeries = serverRequest.getJoinedSeries();
        for(Series series: joinedSeries)
        {
            seriesMenuPanel.add(Box.createVerticalStrut(15));
            seriesMenuPanel.add(new SeriesPanel(series));
        }

        JScrollPane seriesMenuScrollPane = new JScrollPane(seriesMenuPanel);

        // TODO : Create the Top Menü Panel

        JPanel topMenuePanel = new JPanel();
        topMenuePanel.setBackground(Color.blue);
        topMenuePanel.setPreferredSize(new Dimension(300, 50));
        
        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        
        postPanel.setBackground(Color.BLACK);


        // TODO : Create User Login

        // TODO : Add ability to change Episodes

        // TODO : Add Series Display
        // TODO : Add Search
        // TODO : Add Image Posts
        // TODO : Fix all Try statements and add Error statements
        // TODO : Fix buge when Closing comment/post resizing bugs out





        scrollPane = new JScrollPane(postPanel);
        


        spawnInitialPosts();



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
                if (value >= maximum - 200 && scrollPane.isVisible()) {
                    spawnNewPost();
                    postPanel.revalidate();
                    postPanel.repaint();
                    scrollPane.revalidate();
                    scrollPane.repaint();
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
        frame.add(seriesMenuScrollPane, BorderLayout.WEST);
        frame.add(topMenuePanel, BorderLayout.NORTH);
       
        
        
        
        
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
            
            postPanel.add(Box.createVerticalStrut(50));
            postPanel.add(new PostPanel(posts.get(0), scrollPane, serverRequest));
            
            


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    
}
