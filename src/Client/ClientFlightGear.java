package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientFlightGear implements Client {

	String ip;
	int port;
	SpecificRequest sr;
	
	public ClientFlightGear(String ip,int port) {
		this.ip=ip;
		this.port=port;
	}
	
	
	@Override
	public void start(SpecificRequest sr) {
		
		//System.out.println("Opening New Tread");
        this.sr = sr;
        //Lambda Expression Override run/Start
        new Thread(() -> {
            try {
                this.runClient(this.ip,this.port);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
	}

	
	
	void runClient(String ip, int port) throws UnknownHostException, IOException {
		
		//Streams
		PrintWriter out = null;
	    BufferedReader in = null;
	    
	    //Our Socket that our client will write to and get requests
	    Socket socket=new Socket(ip,port);
	    //set the specific request
	    
	    this.sr.handleRequest(socket.getInputStream(),socket.getOutputStream());
	    System.out.println("closing socket of ClientFlightGear");
	    socket.close();
	    }
	}
