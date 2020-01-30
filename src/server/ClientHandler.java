
package server;

import java.io.OutputStream;
import java.io.InputStream;

public interface ClientHandler
{
    void handleClient(InputStream in, OutputStream out);
}
