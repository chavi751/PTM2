
package server;

public interface Server
{
    void open();
    
    void start(ClientHandler p0);
    
    void stop();
}
