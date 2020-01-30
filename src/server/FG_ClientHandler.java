package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;


public class FG_ClientHandler implements ClientHandler {

	
	private volatile boolean stop;
	int timePerSecond=10;
	//ConcurrentHashMap
	//used to hold all the parameters that was send from FG by using our XML file in generic protocol
	static public ConcurrentHashMap<String,Double> pathAndValueFromFG = new ConcurrentHashMap<String,Double>();
	//will hold the String from Server
	String fromServerValue;
	String[] paths;
	String[] splitedArray;
	
	//need to be changed to initializePath() when used with FG
	private FG_ClientHandler() {initializePaths();}
	
	//singleton design patterns of FG_ClientHandler
	private static class FG_ClientHandlerHolder {public static final FG_ClientHandler helper = new FG_ClientHandler();}
	public static FG_ClientHandler getHelper() {return FG_ClientHandlerHolder.helper;}
	
	public void setTimePerSecond(int timePerSecond) {this.timePerSecond=timePerSecond;}
	public void stop() {this.stop=true;}
	
	
	
	//here we put all the paths that we will use in our XML small_generic file
	private void initializePaths() {
		this.paths=new String[]{
				"/instrumentation/airspeed-indicator/indicated-speed-kt",
				"/instrumentation/altimeter/indicated-altitude-ft",
				"/instrumentation/altimeter/pressure-alt-ft",
				"/instrumentation/attitude-indicator/indicated-pitch-deg",
				"/instrumentation/attitude-indicator/indicated-roll-deg",
				"/instrumentation/attitude-indicator/internal-pitch-deg",
				"/instrumentation/attitude-indicator/internal-roll-deg",
				"/instrumentation/encoder/indicated-altitude-ft",
				"/instrumentation/encoder/pressure-alt-ft",
				"/instrumentation/gps/indicated-altitude-ft",
				"/instrumentation/gps/indicated-ground-speed-kt",
				"/instrumentation/gps/indicated-vertical-speed",
				"/instrumentation/heading-indicator/heading-bug-error-deg",
				"/instrumentation/magnetic-compass/indicated-heading-deg",
				"/instrumentation/slip-skid-ball/indicated-slip-skid",
				"/instrumentation/turn-indicator/indicated-turn-rate",
				"/instrumentation/vertical-speed-indicator/indicated-speed-fpm",
				"/controls/flight/aileron",
				"/controls/flight/elevator",
				"/controls/flight/rudder",
				"/controls/flight/flaps",
				"/controls/engines/engine/throttle",
				"/engines/engine/rpm",
				"/controls/flight/speedbrake",
				"/instrumentation/heading-indicator/offset-deg"};
		//we need this because first time we use put
		//then replace
		for(int i=0;i<this.paths.length;i++)
			pathAndValueFromFG.put(this.paths[i], 0.0);
		
	}
	/*
	private void initializePathsForTest() {
		this.paths=new String[]{"simX","simY","simZ"};
		//we need this because first time we use put
		//then replace
		for(int i=0;i<this.paths.length;i++)
			pathAndValueFromFG.put(this.paths[i], 0.0);
		
	}*/
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		
		this.stop=false;
		//System.out.println("inside ClientHandlerFG");
		BufferedReader serverInput = new BufferedReader(new InputStreamReader(in));
		
		while(!stop)
		try {
			//System.out.println(serverInput.readLine());
			fromServerValue=serverInput.readLine();
			//System.out.println("from server: "+fromServerValue);
			if(fromServerValue!=null)
			splitedArray=fromServerValue.split(",");
			//System.out.println(splitedArray.length);
			if(splitedArray.length==25)//for test= 25\3
				for(int i=0;i<splitedArray.length;i++)//we insert to our hashMap <the path, and the value>
					pathAndValueFromFG.replace(this.paths[i],Double.parseDouble(splitedArray[i]));
			Thread.sleep(1000/timePerSecond);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("client handler closed");
	}

}
