import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerRequest 
{
    private  ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    
    
    public ServerRequest(Socket socket) throws Exception
    {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }
    

    public void addPost(Post post) throws Exception
    {
        Object[] arg = new Object[] {post};
        Message message = new Message(1,"addPost", arg);
        outputStream.writeObject(message);
        outputStream.flush();
    }

    public Post getPostInfo(int postID) throws Exception
    {
        Object[] arg = new Object[] {postID};
        Message message = new Message(1,"getPostInfo", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Post)answer.data;
    }

    public List<Post> getPosts(int count, Integer series, SortBy sortBy) throws Exception
    {
        Object[] arg = new Object[] {count, series, sortBy};
        Message message = new Message(1,"getPosts", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (List<Post>)answer.data;
    }

    public int addComment(Comment comment) throws Exception
    {
        Object[] arg = new Object[] {comment};
        Message message = new Message(1,"addComment", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (int)answer.data;
    }


    public Comment getCommentInfo(int commentID) throws Exception
    {
        Object[] arg = new Object[] {commentID};
        Message message = new Message(1,"getCommentInfo", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Comment)answer.data;
    }

    public List<Comment> getComments(int count, int postId, SortBy sortBy) throws Exception
    {
        Object[] arg = new Object[] {count, postId, sortBy};
        Message message = new Message(1,"getComments", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (List<Comment>)answer.data;
    }

    public List<Comment> getSubComments(int count, int parentID, SortBy sortBy) throws Exception
    {
        Object[] arg = new Object[] {count, parentID, sortBy};
        Message message = new Message(1,"getSubComments", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (List<Comment>)answer.data;
    }

    public List<Comment.CommentLevel> getAllSubComments(List<Comment> comments, int indentLevel) throws Exception
    {
        Object[] arg = new Object[] {comments, indentLevel};
        Message message = new Message(1,"getAllSubComments", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (List<Comment.CommentLevel>)answer.data;
    }

    public Integer getSeriesID(String name) throws Exception
    {
        Object[] arg = new Object[] {name};
        Message message = new Message(1,"getSeriesID", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Integer)answer.data;
    }


    public Series getSeriesInfo(int id) throws Exception
    {
        Object[] arg = new Object[] {id};
        Message message = new Message(1,"getSeriesInfo", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Series)answer.data;
    }

    public Integer getUserIDFromUsername(String username) throws Exception
    {
        Object[] arg = new Object[] {username};
        Message message = new Message(1,"getUserIDFromUsername", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Integer)answer.data;
    }

    public Integer getUserIDFromEmail(String email) throws Exception
    {
        Object[] arg = new Object[] {email};
        Message message = new Message(1,"getUserIDFromEmail", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (Integer)answer.data;
    }

    public User getUserInfo(int userID) throws Exception
    {
        Object[] arg = new Object[] {userID};
        Message message = new Message(1,"getUserInfo", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (User)answer.data;
    }

    public void addUser(User user, int password) throws Exception
    {
        Object[] arg = new Object[] {user, password};
        Message message = new Message(1,"addUser", arg);
        outputStream.writeObject(message);
        outputStream.flush();
    }


    public void addUserEpisode(UserEpisode userEpisode) throws Exception
    {
        Object[] arg = new Object[] {userEpisode};
        Message message = new Message(1,"addUserEpisode", arg);
        outputStream.writeObject(message);
        outputStream.flush();
    }


    public boolean  logIn(String email, int password) throws Exception
    {
        Object[] arg = new Object[] {email, password};
        Message message = new Message(1,"logIn", arg);
        outputStream.writeObject(message);
        outputStream.flush();
        Message answer = (Message)inputStream.readObject();
        return (boolean)answer.data;
    }

    
}
