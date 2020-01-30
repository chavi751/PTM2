package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;


//From here we send request to set vars on SimulatorFlightGear by using massage to server
public class SetParametersRequest implements SpecificRequest {

	private volatile boolean stop;
	public volatile  String massageToServer=null;
	public String massageFromServer=null;
	PrintWriter outToServer;
	BufferedReader serverInput;
	public  boolean turnOn = false;//if there is a server that wait for our massage
	
	
	private SetParametersRequest(){};
	//singleton design patterns of FG_ClientHandler
	private static class GetParametersHolder {public static final SetParametersRequest helper = new SetParametersRequest();}
	public static SetParametersRequest getHelper() {return GetParametersHolder.helper;}
	
	
	
	
	
	public void setMassage(String massage) {this.massageToServer=massage;}
	public String getMassage() {return this.massageFromServer;}
	public void stop() {this.stop=true;}
	
	
	
	@Override
	public void handleRequest(InputStream in, OutputStream out) {
		//if there is a server that wait for our massage
		turnOn = true;
		stop=false;
		PrintWriter outToServer = new PrintWriter(out);
		//BufferedReader serverInput = new BufferedReader(new InputStreamReader(in));
		
		//System.out.println("inside SetParameters");
		
		//need for FG for autostart
		outToServer.println("nasal \r\n" + 
					"c172p.autostart(); \r\n" + 
					"##EOF##");
		outToServer.flush();
		outToServer.println("");
		outToServer.flush();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {e1.printStackTrace();}
		
		while(!stop) {
			 if(massageToServer!=null) {
				outToServer.println(massageToServer);
				outToServer.flush();
				System.out.println(massageToServer);
				setMassage(null);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}

	}
		turnOn=false;
	}
	/*synchronized void send() {	
		
	
}*/
}
