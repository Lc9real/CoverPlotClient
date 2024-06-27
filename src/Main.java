import java.net.Socket;
import java.util.List;

public class Main {
    
    public static String ipAdress = "localhost";
    public static int port = 9806;
    static Socket socket;
    
    public static void main(String[] args) throws Exception {
        try
        {
           

            System.out.println("Starting Client...");
            socket = new Socket(ipAdress, port);
            System.out.println("Connected to Server :)");

            ServerRequest serverRequest = new ServerRequest(socket);



            

             
            List<Post> posts = serverRequest.getPosts(10, 1, SortBy.VOTES);
            for(Post post : posts)
            {
                System.out.println(post);
            }

        


            


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            socket.close();
        }
    }


    
    private String test()
    {
        return "";
    }
}
