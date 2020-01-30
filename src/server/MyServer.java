// 
// Decompiled by Procyon v0.5.36
// 

package server;

import java.net.Socket;
import java.net.SocketTimeoutException;



import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyServer implements Server
{
	
	//Members
    private int port;
    private volatile boolean stop;
    //Different ClientHandlers
    private ClientHandler ch;
    
    
    //CON
    public MyServer(final int port) {
        this.port = port;
        this.stop = false;
    }
    
    //Opening New Thread for each Client 
    //initialize	 specific clientHeandler
    public void start(final ClientHandler ch) {
    	//System.out.println("Opening New Tread");
        this.ch = ch;
        //Lambda Expression Override run/Start
        new Thread(() -> {
            try {
                this.runServer(this.port);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    

    synchronized public void runServer(final int port) throws IOException {
    	System.out.println("Wait for client");
    	ServerSocket socket = new ServerSocket(port); 
    	//wait, on welcoming socket for contact by client
        Socket connectionSocket = socket.accept();
        System.out.println("Client coneccted");
        //Specific request ClientHandler
	    this.ch.handleClient(connectionSocket.getInputStream(),connectionSocket.getOutputStream());
	    System.out.println("closing socket");
	    socket.close();
	    
    }
    
    public void open() {
    }
    
    public void stop() {
        this.stop = true;
    }
}
