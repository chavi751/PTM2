package flightgear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class FlightGearClient {
    public int port;
    public String ip;
    public Socket socket;
    public PrintWriter writer;
    public BufferedReader reader;

    public FlightGearClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        socket = null;
        writer = null;
        reader = null;
    }

    public void run_client(){
        try{
            socket = new Socket(ip, port);
            reader = new  BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send_command(String data) {
        writer.write(data + "\r\n");
        writer.flush();
    }

    public void stopClient() throws IOException {
        socket.close();
    }
}
