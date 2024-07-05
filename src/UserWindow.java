
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private static User user;

    public static void main(String[] args) throws Exception
    {   
        
        System.out.println("Starting Client...");
        socket = new Socket(ipAdress, port);
        System.out.println("Connected to Server :)");
        serverRequest = new ServerRequest(socket);
        
        
        

        JPanel seriesMenuPanel = new JPanel();
        seriesMenuPanel.setBackground(backgroundColor);
        seriesMenuPanel.setForeground(lineColor);
        seriesMenuPanel.setPreferredSize(new Dimension(200, 300));
        seriesMenuPanel.setLayout(new BoxLayout(seriesMenuPanel, BoxLayout.Y_AXIS));

        

        JScrollPane seriesMenuScrollPane = new JScrollPane(seriesMenuPanel);

        // TODO : Create the Top Menü Panel

        JPanel topMenuePanel = new JPanel();
        topMenuePanel.setBackground(Color.blue);
        topMenuePanel.setPreferredSize(new Dimension(800, 50));
        topMenuePanel.setLayout(new BoxLayout(topMenuePanel, BoxLayout.X_AXIS));

        topMenuePanel.add(new SearchbarPanel(), JPanel.CENTER_ALIGNMENT);




        


        // TODO : Create User Login

        // TODO : Add ability to change Episodes

        // TODO : Add Series Display
        // TODO : Add Search
        // TODO : Add Image Posts
        // TODO : Fix all Try statements and add Error statements
        // TODO : Add Votes




        scrollPane = new postScrollPane(serverRequest);
        postPanel = (JPanel)scrollPane.getViewport().getView();

        List<Series> joinedSeries = serverRequest.getJoinedSeries();
        for(Series series: joinedSeries)
        {
            seriesMenuPanel.add(Box.createVerticalStrut(15));
            seriesMenuPanel.add(new SeriesPanel(series, scrollPane, serverRequest));
        }
        

        
        


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

    

    
}
