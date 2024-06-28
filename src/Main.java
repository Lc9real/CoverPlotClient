import java.net.Socket;

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

            
            UserInteface userInteface = new UserInteface();

            userInteface.Start(socket);
            


            


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
